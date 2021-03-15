package com.farmmart.data.repository;

import com.farmmart.data.model.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Slf4j
@Sql(scripts = {"classpath:db/insert.sql"})
class SupplierRepositoryTest {

    @Autowired
    CountryRepository countryRepository;
    Country country;

    @Autowired
    ProvinceRepository provinceRepository;
    Province province;

    @Autowired
    LocalGovernmentOrCountyRepository localGovernmentOrCountyRepository;
    LocalGovernmentOrCounty localGovernmentOrCounty;

    @Autowired
    AddressRepository addressRepository;
    Address address;

    @Autowired
    SupplierRepository supplierRepository;
    Supplier supplier;

    @Autowired
    ProductRepository productRepository;
    Product product;
    Product product1;

    @BeforeEach
    void setUp() {
        address =new Address();
        localGovernmentOrCounty =new LocalGovernmentOrCounty();
        supplier = new Supplier();
        product = new Product();
        product1 = new Product();
        province = new Province();
        country = new Country();
    }

    @Test
    void testThatYouCanSaveSupplier(){
        localGovernmentOrCounty =localGovernmentOrCountyRepository.findById(1).orElse(null);
        address.setLocalGovernmentOrCounty(localGovernmentOrCounty);
        address.setPostZipCode("110001");
        address.setStreetName("Osanyin Street Alagomeji");
        address.setCity("Yaba");
        address.setStreetNumber("6");
        address.setAddressType(AddressType.OFFICE);


        supplier.getAddresses().add(address);//Test it(to work on this, if null it should throw exception)
        supplier.setEmailAddress("oluwalogbon@gmail.com");//test it
        supplier.setSupplierCategory(SupplierCategory.PRODUCT_SUPPLIER);//test it
        supplier.setPhoneNumber("08096743213");//test it
        supplier.setPassword("oluwalogbon1234");
        supplier.setCompanyName("Oluwalogbon Enterprises");
        supplier.setFacility(FacilityType.OWN);//Test it
        supplier.setNatureOfBusiness("Plumbing services, irrigation equipment supply and accessories");
        supplier.setRcNumber("RC12345");
        supplier.setTaxIdentificationNumber("1264892448000977");

        log.info("Supplier repo before saving -->{}",supplier);

        assertDoesNotThrow(()->addressRepository.saveAddress(address));
        assertDoesNotThrow(()->supplierRepository.saveSupplier(supplier));

        assertNotNull(supplier);

        log.info("Supplier repo before saving -->{}",supplier);

    }

    @Test
    void testThatYouCanSaveProductsToSupplier(){

        country.setCountryCode("+234");
        country.setCountryName("Nigeria");

        province.setCountry(country);
        province.setProvinceCode("15");
        province.setProvinceName("Lagos");

        localGovernmentOrCounty.setProvince(province);
        localGovernmentOrCounty.setLocalGovernmentOrCountyCode("15.14");
        localGovernmentOrCounty.setLocalGovernmentOrCountyName("Lagos Island");

        LocalGovernmentOrCounty localGovernmentOrCounty1 = new LocalGovernmentOrCounty();
        localGovernmentOrCounty1.setProvince(province);
        localGovernmentOrCounty1.setLocalGovernmentOrCountyCode("15.04");
        localGovernmentOrCounty1.setLocalGovernmentOrCountyName("Amuwo Odofin");

        address.setLocalGovernmentOrCounty(localGovernmentOrCounty);
        address.setPostZipCode("101001");
        address.setStreetName("Fakolujo Street");
        address.setCity("lagos");
        address.setStreetNumber("1");
        address.setAddressType(AddressType.OFFICE);

        Address address1 = new Address();

        address1.setAddressType(AddressType.OFFICE);
        address1.setStreetNumber("202");
        address1.setCity("Amuwo Odofin");
        address1.setStreetName("Amuwo Odofine Industrial Estate Mile 2");
        address1.setPostZipCode("100001");
        address1.setLocalGovernmentOrCounty(localGovernmentOrCounty1);

        Set<Address> addressSet = new HashSet<>();
        addressSet.add(address);
        addressSet.add(address1);

        supplier.setAddresses(addressSet);
        supplier.setPassword("skukoom@soj1234");
        supplier.setCompanyName("Skukoom Nigeria Limited");
        supplier.setEmailAddress("info@skukoom.com");
        supplier.setTaxIdentificationNumber("245689001986523");
        supplier.setRcNumber("RC24574");
        supplier.setNatureOfBusiness("Vegetable farming");
        supplier.setPhoneNumber("08123457612");
        supplier.setSupplierCategory(SupplierCategory.PRODUCT_SUPPLIER);
        supplier.setFacility(FacilityType.OWN);

        product.setProductName("Tomatoes");
        product.setProductCategory(ProductCategory.VEGETABLE_CROPS);
        product.setProductDescription("Tomatoes belongs to the night shade family");
        product.setProductColour("Red");
        //product.setProductSize(ProductSize.MEDIUM);
        product.setProductStockQuantity(BigDecimal.valueOf(500));
        product.setProductUnitOfMeasure(UnitOfMeasure.BAG);
        product.setProductPrice(BigDecimal.valueOf(100000.00));
        product.setSupplier(supplier);

        product1.setProductName("Garden Egg");
        product1.setProductCategory(ProductCategory.VEGETABLE_CROPS);
        product1.setProductDescription("Garden egg belongs to the night shade family");
        product1.setProductColour("Purple");
        //product1.setProductSize(ProductSize.LARGE);
        product1.setProductStockQuantity(BigDecimal.valueOf(500));
        product1.setProductUnitOfMeasure(UnitOfMeasure.BAG);
        product1.setProductPrice(BigDecimal.valueOf(150000.00));
        product1.setSupplier(supplier);

        Set<Product> products =new HashSet<>();

        products.add(product);
        products.add(product1);



        log.info("Product repo before saving -->{}",products);

        assertDoesNotThrow(()->countryRepository.save(country));
        assertDoesNotThrow(()->provinceRepository.saveProvince(province));
        assertDoesNotThrow(()->localGovernmentOrCountyRepository.saveLocalGovernmentOrCounty(localGovernmentOrCounty1));
        assertDoesNotThrow(()->localGovernmentOrCountyRepository.saveLocalGovernmentOrCounty(localGovernmentOrCounty));
        assertDoesNotThrow(()->addressRepository.saveAddress(address1));
        assertDoesNotThrow(()->addressRepository.saveAddress(address));
        assertDoesNotThrow(()->productRepository.saveProduct(product));
        assertDoesNotThrow(()->productRepository.saveProduct(product1));
        assertDoesNotThrow(() ->supplierRepository.saveSupplier(supplier));

        log.info("Product repo after saving -->{}",products);

    }


    @Test
    void testThatYouCanFindSupplierById(){

        supplier=supplierRepository.findById(1).orElse(null);

        assertNotNull(supplier.getId());

        log.info("Details of Supplier with ID 1 is -->{}",supplier);

    }

    @Test
    void testThatYouCanFindAllSuppliers(){

        List<Supplier> supplierList =supplierRepository.findAll();

        log.info("List of Suppliers -->{}",supplier);
    }

    @Test
    void testThatYouCanUpdateSupplierById(){
        supplier =supplierRepository.findById(1).orElse(null);

        assertNotNull(supplier);

        supplier.setCompanyName("Logic Gate Integrated Services Limited");
        supplier.setRcNumber("RC26548");
        supplier.setTaxIdentificationNumber("23678085543577");
        supplier.setEmailAddress("lgisltd@outlook.com");
        supplier.setPassword("logicCompany@foryou1970");

        assertThat(supplier.getCompanyName()).isEqualTo("Logic Gate Integrated Services Limited");
        assertThat(supplier.getEmailAddress()).isEqualTo("lgisltd@outlook.com"); //test it
        assertThat(supplier.getTaxIdentificationNumber()).isEqualTo("23678085543577");
        assertThat(supplier.getRcNumber()).isEqualTo("RC26548");
        assertThat(supplier.getPassword()).isEqualTo("logicCompany@foryou1970");

        log.info("Supplier with ID 1 updated record is: -->{}",supplier);

    }

    @Test
    void testThatYouCanDeleteSupplierById(){
        supplierRepository.deleteById(1);

        log.info("Supplier with ID 1 updated record is: -->{}",supplier.getId());
    }

    @Test
    void testThatYouCanDeleteAllSupplier(){
        supplierRepository.deleteAll();

        log.info("Supplier record is: -->{}",supplier);
    }


}