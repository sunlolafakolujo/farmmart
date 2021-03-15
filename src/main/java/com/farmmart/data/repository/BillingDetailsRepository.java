package com.farmmart.data.repository;

import com.farmmart.data.exception.BillingDetailsException;
import com.farmmart.data.model.BillingDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillingDetailsRepository extends JpaRepository<BillingDetail,Integer> {

    public default BillingDetail saveBillingDetails(BillingDetail billingDetails) throws BillingDetailsException {
        if(!isBillingAddressDetailsValid(billingDetails)){
            throw new BillingDetailsException("Billing Address cannot be null");
        }

        if(!isBillingPaymentCardInformationValid(billingDetails)){
            throw new BillingDetailsException("Billing Payment Card Information cannot be null");
        }

        if(!invalidPhoneNumber(billingDetails)){
            throw new BillingDetailsException("Invalid Phone Number");
        }

        return save(billingDetails);
    }

    private boolean isBillingAddressDetailsValid(BillingDetail billingDetail){
        if(billingDetail.getAddresses() ==null){
            return false;
        }
        return true;
    }

    private boolean isBillingPaymentCardInformationValid(BillingDetail billingDetail){
        if(billingDetail.getPaymentCardInformationSet() ==null){
            return false;
        }
        return true;
    }

    private boolean invalidPhoneNumber(BillingDetail billingDetails){
        if(billingDetails.getPhoneNumber().matches("\\d{10}"))
            return true;
        else if(billingDetails.getPhoneNumber().matches("\\d{11}"))
            return true;
        else if(billingDetails.getPhoneNumber().matches("\\d{3}[-\\.\\s]\\d{3}[-\\.\\s]\\d{4}"))
            return true;
        else if(billingDetails.getPhoneNumber().matches("\\d{3}-\\d{3}-\\d{4}\\s(x|(ext))\\d{3,5}"))
            return true;
        else if(billingDetails.getPhoneNumber().matches("\\(\\d{3}\\)-\\d{3}-\\d{4}"))
            return true;
        else
            return false;
    }


}
