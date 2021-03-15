package com.farmmart.data.repository;

import com.farmmart.data.exception.PaymentCardInformationException;
import com.farmmart.data.model.PaymentCardInformation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentCardInformationRepository extends JpaRepository<PaymentCardInformation,Integer> {

    public default PaymentCardInformation savePaymentCardInformation(PaymentCardInformation paymentCardInformation) throws PaymentCardInformationException {
        if(!isPaymentCardInformationValid(paymentCardInformation)){
            throw new PaymentCardInformationException("Payment Method cannot be null");
        }

        if(!invalidNumberOfCardDigitNumber(paymentCardInformation)){
            throw new PaymentCardInformationException("The number digit on the payment card should be 16");
        }


        return save(paymentCardInformation);
    }

    private boolean isPaymentCardInformationValid(PaymentCardInformation paymentCardInformation){
        if(paymentCardInformation.getPaymentMethod() == null){
            return false;
        }else
            return true;
    }

    private boolean invalidNumberOfCardDigitNumber(PaymentCardInformation paymentCardInformation){

        if(paymentCardInformation.getPaymentCardNumber().length() != 16){
            return false;
        }
        return true;
    }

}
