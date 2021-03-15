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

@SpringBootTest
@Slf4j
@Sql(scripts = {"classpath:db/insert.sql"})
class CustomerRepositoryTest {

    @Autowired
    CustomerRepository customerRepository;
    Customer customer;

    @Autowired
    AddressRepository addressRepository;
    Address billingAddress;

    @Autowired
    LocalGovernmentOrCountyRepository localGovernmentOrCountyRepository;
    LocalGovernmentOrCounty localGovernmentOrCounty;

    @Autowired
    PaymentCardInformationRepository paymentCardInformationRepository;
    PaymentCardInformation paymentCardInformation;

    @Autowired
    BillingDetailsRepository billingDetailsRepository;
    BillingDetail billingDetails;

    @BeforeEach
    void setUp() {
        customer = new Customer();
        billingAddress = new Address();
        localGovernmentOrCounty = new LocalGovernmentOrCounty();
        billingDetails = new BillingDetail();
        paymentCardInformation = new PaymentCardInformation();
    }

    @Test
    void testThatYouCanSaveCustomer(){
        localGovernmentOrCounty = localGovernmentOrCountyRepository.findById(1).orElse(null);

        paymentCardInformation.setCardExpirationMonth(3);
        paymentCardInformation.setCardCVV(234);
        paymentCardInformation.setNameOnCard("Abosede Fakolujo");
        paymentCardInformation.setPaymentCardNumber("1234654103211341");//Test to fail
        paymentCardInformation.setPaymentMethod(PaymentMethod.MASTER_CARD);//Test to fail
        paymentCardInformation.setCardExpirationYear(22);

        Set<PaymentCardInformation> paymentCardInformationSet =new HashSet<>();
        paymentCardInformationSet.add(paymentCardInformation);

        billingAddress.setLocalGovernmentOrCounty(localGovernmentOrCounty);//Test to fail
        billingAddress.setAddressType(AddressType.BILLING);
        billingAddress.setCity("Ajeromi Ifelodun");
        billingAddress.setStreetNumber("214");
        billingAddress.setStreetName("Aladelola Street");
        billingAddress.setPostZipCode("100001");

        Set<Address> addressSet = new HashSet<>();
        addressSet.add(billingAddress);

        billingDetails.setPaymentCardInformationSet(paymentCardInformationSet); //test to fail
        billingDetails.setAddresses(addressSet); //test to fail
        billingDetails.setReceiverName("Oluwadara Fakolujo");
        billingDetails.setPhoneNumber("08143535328");// Test to fail

        customer.setPassword("ololomale");
        customer.setBillingDetails(billingDetails);
        customer.setEmailAddress("dap@live.com");//Test to fail
        customer.setGender(Gender.FEMALE);
        customer.setDayOfBirth(22);
        customer.setMonthOfBirth(6);
        customer.setYearOfBirth(1975);
        customer.setPhoneNumber("08080472478");
        customer.setLastName("Fakolujo");
        customer.setFirstName("Abosede");
        customer.addAddress(billingAddress); //test to fail
        customer.setOtherNames("Adebukola");

        //assertEquals(45,customer.age());

        log.info("Customer repo before saving -->{}",customer);

        assertDoesNotThrow(() ->localGovernmentOrCountyRepository.saveLocalGovernmentOrCounty(localGovernmentOrCounty));
        assertDoesNotThrow(() -> addressRepository.saveAddress(billingAddress));
        assertDoesNotThrow(() ->paymentCardInformationRepository.savePaymentCardInformation(paymentCardInformation));
        assertDoesNotThrow(() ->billingDetailsRepository.saveBillingDetails(billingDetails));
        assertDoesNotThrow(() ->customerRepository.saveCustomer(customer));

        log.info("Customer repo after saving -->{}",customer);

    }

    @Test
    void testThatYouCanFindCustomerById(){
        customer =customerRepository.findById(1).orElse(null);

        assertThat(customer.getId()).isNotNull();

        log.info("Details of Customer with ID 1 -->{}",customer);
    }

    @Test
    void testThatYouCanFindAllCustomer(){
        List<Customer> customerList = customerRepository.findAll();

        log.info("Customers Details -->{}",customerList);
    }

    @Test
    void testThatYouCanUpdateCustomerById(){
        customer =customerRepository.findById(1).orElse(null);

        assertThat(customer.getId()).isNotNull();

        customer.setOtherNames("Oluwafolawemi"); //test it

        assertDoesNotThrow(() ->customerRepository.saveCustomer(customer));

        log.info("Customer with ID updated record is -->{}",customer);

        assertThat(customer.getOtherNames()).isEqualTo("Oluwafolawemi");

    }

    @Test
    void testThatYouCanDeleteCustomerById(){

        customerRepository.deleteById(1);

        assertThat(customer.getId()).isNull();

        log.info("Customer with ID -->{}",customer.getId());

    }

    @Test
    void testThatYouCanDeleteAllCustomer(){

        customerRepository.deleteAll();

        assertThat(customer.getId()).isNull();

        log.info("Customer with ID -->{}",customer.getId());


    }


}