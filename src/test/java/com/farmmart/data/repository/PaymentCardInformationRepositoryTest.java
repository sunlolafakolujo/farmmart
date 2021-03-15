package com.farmmart.data.repository;

import com.farmmart.data.model.PaymentCardInformation;
import com.farmmart.data.model.PaymentMethod;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Slf4j
@Sql(scripts = {"classpath:db/insert.sql"})
class PaymentCardInformationRepositoryTest {

    @Autowired
    PaymentCardInformationRepository paymentCardInformationRepository;
    PaymentCardInformation paymentCardInformation;

    @BeforeEach
    void setUp() {
        paymentCardInformation = new PaymentCardInformation();
    }

    @Test
    void testThatICanSavePaymentCardInformation(){

        paymentCardInformation.setPaymentMethod(PaymentMethod.MASTER_CARD);
        paymentCardInformation.setNameOnCard("Sunlola E. Fakolujo");
        paymentCardInformation.setPaymentCardNumber("5407613104017874");//test it
        paymentCardInformation.setCardExpirationMonth(9);
        paymentCardInformation.setCardExpirationYear(22);
        paymentCardInformation.setCardCVV(262);

        log.info("Payment Card Information repo before saving -->{}",paymentCardInformation);
        assertDoesNotThrow(() ->paymentCardInformationRepository.savePaymentCardInformation(paymentCardInformation));

        assertThat(paymentCardInformation.getId()).isNotNull();

        log.info("Payment Card Information repo after saving -->{}",paymentCardInformation);
    }

    @Test
    void testThatYouCanFindPaymentCardInformationById(){
        paymentCardInformation =paymentCardInformationRepository.findById(1).orElse(null);

        assertNotNull(paymentCardInformation);

        log.info("Payment Card Information with Id 1 -->{}",paymentCardInformation);
    }

    @Test
    void testThatYouCanFindAllPaymentCardInformation(){

        List<PaymentCardInformation> paymentCardInformationList = paymentCardInformationRepository.findAll();

        log.info("List of payment card -->{}",paymentCardInformationList);
    }

    @Test
    void testThatYouCanUpdatePaymentCardInformation(){

        paymentCardInformation = paymentCardInformationRepository.findById(1).orElse(null);

        paymentCardInformation.setPaymentCardNumber("0876521084321196");
        paymentCardInformation.setCardExpirationYear(25);
        paymentCardInformation.setCardExpirationMonth(2);
        paymentCardInformation.setCardCVV(741);

        assertDoesNotThrow(() ->paymentCardInformationRepository.savePaymentCardInformation(paymentCardInformation));

        assertThat(paymentCardInformation.getPaymentCardNumber()).isEqualTo("0876521084321196");
        assertThat(paymentCardInformation.getCardExpirationMonth()).isEqualTo(2);
        assertThat(paymentCardInformation.getCardExpirationYear()).isEqualTo(25);
        assertThat(paymentCardInformation.getCardCVV()).isEqualTo(741);

        log.info("Updated payment card Information id 1 -->{}",paymentCardInformation);
    }

    @Test
    void testThatYouCanDeletePaymentCardInformationById(){

        paymentCardInformationRepository.deleteById(1);

        assertThat(paymentCardInformation.getId()).isNull();

        log.info("Payment Card Information Id 1 is -->{}",paymentCardInformation.getId());
    }

    @Test
    void testThatYouCanDeleteAllPaymentCardInformation(){

        paymentCardInformationRepository.deleteAll();

        assertThat(paymentCardInformation.getId()).isNull();

        log.info("Payment Card Information Id 1 is -->{}",paymentCardInformation.getId());


    }


}