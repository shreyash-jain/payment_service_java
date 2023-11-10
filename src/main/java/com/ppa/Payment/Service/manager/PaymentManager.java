package com.ppa.Payment.Service.manager;


import com.ppa.Payment.Service.dto.PaymentDto;
import com.ppa.Payment.Service.model.Payment;
import com.ppa.Payment.Service.repository.AccountRepository;
import com.ppa.Payment.Service.repository.PaymentRepository;
import com.ppa.Payment.Service.service.IPaymentProvider;
import com.ppa.Payment.Service.service.PaymentFactory;
import com.ppa.Payment.Service.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

@Component
public class PaymentManager {


    @Autowired
    PaymentRepository paymentRepository;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    PaymentFactory paymentFactory;

    @Autowired
    private PlatformTransactionManager transactionManager;


    @Autowired
    PaymentService paymentService;

    private static final int MAX_RETRIES = 3;


    public Payment processPayment(PaymentDto paymentDto) {

        // Todo : validate the request

        IPaymentProvider paymentProvider = paymentFactory.getPlan(paymentDto.getType());
        Payment payment = paymentService.createInitalPayment(paymentDto);
        paymentRepository.save(payment);
        TransactionTemplate transactionTemplate = new TransactionTemplate(transactionManager);

        transactionTemplate.execute(new TransactionCallback<Boolean>() {
            @Override
            public Boolean doInTransaction(TransactionStatus status) {
                payment.setStatus("PENDING");
                paymentRepository.save(payment);

                int retries = 0;
                boolean confirmed = false;

                // retry the provider part
                while (retries < MAX_RETRIES && !confirmed) {
                    try {
                        Boolean paymentResponse = paymentProvider.processPayment(payment);

                        if (paymentResponse != null && paymentResponse) {
                            confirmed = true;
                        } else {
                            retries++;
                            Thread.sleep(1000);
                        }
                    } catch (Exception e) {
                        retries++;
                    }
                }

                if(confirmed) {
                    try {
                        payment.setStatus("COMPLETED");
                        paymentRepository.save(payment);
                        // todo : check if account not present for sender or receiver
                        accountRepository.removeBalance(paymentDto.getSenderId(), paymentDto.getAmount());
                        accountRepository.addBalance(paymentDto.getReceiverId(), paymentDto.getAmount());
                        return true;
                    }
                    catch (Exception e){
                        // todo : add retries
                        Boolean paymentResponse = paymentProvider.refundPayment(payment);
                        status.setRollbackOnly();
                    }
                }
                else {
                    status.setRollbackOnly();
                }

                return false;
            }
        });
        return payment;

    }
}
