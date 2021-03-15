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

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
@Sql(scripts = {"classpath:db/insert.sql"})
class ProductRepositoryTest {

    @Autowired
    SupplierRepository supplierRepository;
    Supplier supplier;

    @Autowired
    ProductRepository productRepository;
    Product product;

    @Autowired
    LocalGovernmentOrCountyRepository localGovernmentOrCountyRepository;
    LocalGovernmentOrCounty localGovernmentOrCounty;

    @Autowired
    AddressRepository addressRepository;
    Address address;

    @BeforeEach
    void setUp() {
        supplier =new Supplier();
        product=new Product();
        localGovernmentOrCounty = new LocalGovernmentOrCounty();
        address = new Address();
    }

    @Test
    void testThatYouCanSaveProduct(){
        localGovernmentOrCounty=localGovernmentOrCountyRepository.findById(8).orElse(null);

        address.setLocalGovernmentOrCounty(localGovernmentOrCounty);
        address.setPostZipCode("100001");
        address.setStreetName("Adenekan Street");
        address.setCity("Surulere");
        address.setStreetNumber("Plot 234");
        address.setAddressType(AddressType.OFFICE);

        Set<Address> addresses = new HashSet<>();
        addresses.add(address);

        supplier.setFacility(FacilityType.OWN);
        supplier.setSupplierCategory(SupplierCategory.PRODUCT_SUPPLIER);
        supplier.setPhoneNumber("08097451467");
        supplier.setNatureOfBusiness("Supply of farm equipment and irrigation accessories");
        supplier.setRcNumber("RC154367");
        supplier.setTaxIdentificationNumber("467890075433467");
        supplier.setEmailAddress("tjayboss@yahoo.com");
        supplier.setCompanyName("Just Farm Equipment Limited");
        supplier.setPassword("jfqltd@12356#");
        supplier.setAddresses(addresses);

        Set<Supplier> suppliers = new HashSet<>();
        suppliers.add(supplier);
        product.setSuppliers(suppliers);//test it
        product.setProductName("Drip tape");
        product.setProductDescription("1000 feet of 1/2 inch, 36mm emitter spacing for night shade plant");
        product.setProductColour("Black");
        product.setProductCategory(ProductCategory.EQUIPMENT);//test it
        product.setProductDimension("10000 feet Length x 1/2 inch diameter");
        product.setProductPrice(BigDecimal.valueOf(50000.00));//test it
        product.setProductManufacturer("Hydro Flow Technologies");
        product.setProductStockQuantity(BigDecimal.valueOf(1000));
        product.setProductUnitOfMeasure(UnitOfMeasure.FT);//test it
        product.setProductWeight(10.0);
        //product.setSupplier(supplier);

        log.info("Product repo before saving -->{}",product);

        assertDoesNotThrow(()->addressRepository.saveAddress(address));
        assertDoesNotThrow(()->supplierRepository.saveSupplier(supplier));
        assertDoesNotThrow(() ->productRepository.saveProduct(product));

        log.info("Product repo after saving -->{}",product);

    }

    @Test
    void testThatYouCanFindProductById(){
        product=productRepository.findById(1).orElse(null);

        assertNotNull(product);

        log.info("Product with ID 1 is-->{}",product);
    }

    @Test
    void testThatYouCanFindAllProducts(){

        List<Product> productList =productRepository.findAll();

        log.info("All products-->{}",productList);

    }

    @Test
    void testThatYouCanUpdateProduct(){
        product=productRepository.findById(1).orElse(null);

        product.setProductColour("Brown");
        product.setProductName("Yam");
        product.setProductDescription("It's a tuber crop and source of energy and can be converted to other edible food");
        product.setProductCategory(ProductCategory.TUBER_CROPS);
        product.setProductStockQuantity(BigDecimal.valueOf(6000));
        product.setProductPrice(BigDecimal.valueOf(500));
        product.setProductUnitOfMeasure(UnitOfMeasure.PIECE);
        product.setProductSize(ProductSize.MEDIUM);

        assertDoesNotThrow(()->productRepository.saveProduct(product));

        log.info("Product ID 1 update-->{}",product);
    }

    @Test
    void testThatYouCanDeleteProductById(){
        supplierRepository.deleteById(1);
        productRepository.deleteById(1);
        assertNull(product.getId());

        log.info("Product ID is -->{}",product.getId());

    }

    @Test
     void testThatYouCanDeleteAllProduct(){
        supplierRepository.deleteAll();
        productRepository.deleteAll();

        log.info("Product ID is -->{}",product);

    }


}