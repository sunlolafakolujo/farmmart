package com.farmmart.data.repository;

import com.farmmart.data.model.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Slf4j
@Sql(scripts = {"classpath:db/insert.sql"})
class BillingDetailsRepositoryTest {
    @Autowired
    BillingDetailsRepository billingDetailsRepository;
    BillingDetail billingDetails;

    @Autowired
    PaymentCardInformationRepository paymentCardInformationRepository;
    PaymentCardInformation paymentCardInformation;

    @Autowired
    AddressRepository addressRepository;
    Address billingAddress;

    @Autowired
    LocalGovernmentOrCountyRepository localGovernmentOrCountyRepository;
    LocalGovernmentOrCounty localGovernmentOrCounty;

    @Autowired
    CustomerRepository customerRepository;

    @BeforeEach
    void setUp() {

        billingDetails = new BillingDetail();
        paymentCardInformation = new PaymentCardInformation();
        billingAddress = new Address();
        localGovernmentOrCounty = new LocalGovernmentOrCounty();
    }


    @Test
    void testThatYouCanSaveBillingDetails(){
        paymentCardInformation.setPaymentMethod(PaymentMethod.MASTER_CARD);
        paymentCardInformation.setCardExpirationYear(22);
        paymentCardInformation.setPaymentCardNumber("2345678909876543");
        paymentCardInformation.setNameOnCard("Abosede A. Fakolujo");
        paymentCardInformation.setCardCVV(234);
        paymentCardInformation.setCardExpirationMonth(1);

        localGovernmentOrCounty =localGovernmentOrCountyRepository.findById(1).orElse(null);

        billingAddress.setStreetNumber("House 2");
        billingAddress.setStreetName("N Close Sparklight Estate");
        billingAddress.setCity("OPIC");
        billingAddress.setAddressType(AddressType.BILLING);
        billingAddress.setLocalGovernmentOrCounty(localGovernmentOrCounty);
        billingAddress.setPostZipCode("100001");

        Set<Address> addresses = new HashSet<>();
        addresses.add(billingAddress);
        
        Set<PaymentCardInformation> paymentCardInformationSet =new HashSet<>();
        paymentCardInformationSet.add(paymentCardInformation);

        billingDetails.setPaymentCardInformationSet(paymentCardInformationSet);
        billingDetails.setAddresses(addresses);
        billingDetails.getPaymentCardInformationSet().add(paymentCardInformation);
        billingDetails.setPhoneNumber("08080472478");
        billingDetails.setReceiverName("Dara Fakolujo");

        log.info("Billing repo before saving -->{}",billingDetails);

        assertDoesNotThrow(() ->paymentCardInformationRepository.savePaymentCardInformation(paymentCardInformation));
        assertDoesNotThrow(() ->addressRepository.saveAddress(billingAddress));
        assertDoesNotThrow(() ->billingDetailsRepository.saveBillingDetails(billingDetails));


        assertThat(billingDetails.getId()).isNotNull();

        log.info("Billing repo after saving -->{}",billingDetails);

    }

    @Test
    void testThatYouCanFindBillingDetailsById(){

        billingDetails =billingDetailsRepository.findById(1).orElse(null);

        assertNotNull(billingDetails);

        log.info("Billing details with Id 1 is -->{}",billingDetails);
    }

    @Test
    void testThatCanFindAllBillingDetails(){

        List<BillingDetail> billingDetailsList =billingDetailsRepository.findAll();

        log.info("Billing Details -->{}",billingDetailsList);
    }

    @Test
    void testThatYouCanUpdateBillingDetailsById(){

        billingDetails = billingDetailsRepository.findById(1).orElse(null);

        localGovernmentOrCounty =localGovernmentOrCountyRepository.findById(2).orElse(null);

        billingAddress.setPostZipCode("108680");
        billingAddress.setStreetName("Adebisi Street Alagomeji");
        billingAddress.setStreetNumber("4");
        billingAddress.setCity("Yaba");
        billingAddress.setAddressType(AddressType.BILLING);
        billingAddress.setLocalGovernmentOrCounty(localGovernmentOrCounty);

        billingDetails.setPhoneNumber("08055686436"); //test it
        billingDetails.getAddresses().add(billingAddress);//test it

        assertDoesNotThrow(() ->addressRepository.saveAddress(billingAddress));
        assertDoesNotThrow(() ->billingDetailsRepository.saveBillingDetails(billingDetails));

        assertThat(billingDetails.getAddresses()).isEqualTo(billingAddress);
        assertThat(billingDetails.getPhoneNumber()).isEqualTo("08055686436");

        log.info("Updated Billing details Id 1 is -->{}",billingDetails);
    }

    @Test
    void testThatYouCanDeleteBillingDetailsById(){

        paymentCardInformationRepository.deleteById(1);

        customerRepository.deleteById(1);

        billingDetailsRepository.deleteById(1);

        assertThat(billingDetails.getId()).isNull();

        log.info("Billing details ID 1 -->{}",billingDetails);
    }

    @Test
    void testThatYouCanDeleteAllBillingDetails(){

        paymentCardInformationRepository.deleteAll();

        customerRepository.deleteAll();

        billingDetailsRepository.deleteAll();

        log.info("Billing repo is -->{}",billingDetails.getId());
    }

}