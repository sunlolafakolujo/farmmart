SET FOREIGN_KEY_CHECKS  = 0;

truncate table country;
truncate table province;
truncate table local_government_or_county;
truncate table address;
truncate table billing_detail;
truncate table billing_detail_addresses;
truncate table payment_card_information;
truncate table customer;
truncate table customer_addresses;
truncate table supplier;
truncate table product;
truncate table supplier_products;
truncate table customer_order;



insert into country(`id`,`country_name`,`country_code`)
values (1,'Nigeria','+234'),
(2,'Ghana','+233'),
(3,'Republic of Benin','+229'),
(4,'Republic of Zambia','+260'),
(5,'Zimbabwe','+263'),
(6,'CHN','Republic of China');

insert into province(`id`,`province_code`,`province_name`,`country_id`)
values(1,'15','Lagos',1),
(2,'23','Abia',1),
(3,'01','Akwa Ibom',1),
(4,'GH-AH','Ashanti',2),
(5,'GH-AA','Greater Accra',2),
(6,'GH-OT','Oti',2),
(7,'02','Anambra',1);

insert into local_government_or_county(`id`,`local_government_or_county_code`,`local_government_or_county_name`,`province_id`)
values(1,'15.02','Ajeromi Ifelodun',1),
(2,'15.03','Alimosho',1),
(3,'15.04','Amuwo odofin',1),
(4,'23.01','Abia North',2),
(5,'23.13','Ukwa East',2),
(6,'01.03','Eket',3),
(7,'01.29','Uyo',3),
(8,'15.20','Surulere',1);

insert into address(`id`,`address_type`,`city`,`post_zip_code`,`street_name`,`street_number`,`local_government_or_county_id`)
values(1,'BILLING','OPIC','110110','N Close Sparklight Estate Isheri','House 2A',1),
(2,'DELIVERY','Yaba','100001','Herbart Macaulay Way Sabo','312',8),
(3,'BILLING','Mile 2','100001','Amuwo Odofin Housing Estate','Block 202, Flat 2',3),
(4,'OFFICE','Amuwo Odofin','110001','Ganiat Stree Alahun Ozumba Estate','9A',3),
(5,'OFFICE','Ikeja','100011','Isaac John','23A',1),
(6,'OFFICE','Shomolu','110001','Bashua Street','4',2);

insert into billing_detail(`id`,`phone_number`,`receiver_name`)
values(1,'08028424682','Oluwapamilerin Fakolujo'),
(2,'08076543222','Anuoluwapo Macaulay');

insert into billing_detail_addresses(`billing_details_id`,`addresses_id`)
values(1,1),
(2,3);

insert into payment_card_information(`id`,`cardcvv`,`card_expiration_month`,`card_expiration_year`,`name_on_card`,`payment_card_number`,`payment_method`,`billing_details_id`)
values (1,645,10,2024,'Daniel Oluwafolawemi','1234567890145648','MASTER_CARD',1),
(2,546,9,2025,'Emmanuel S Fakolujo','8765123904310945','VISA_CARD',2);

insert into customer(`id`,`date_customer_created`,`date_customer_updated`,`day_of_birth`,`email_address`,`first_name`,`gender`,`last_name`,`month_of_birth`,`other_names`,`password`,`phone_number`,`year_of_birth`,`billing_details_id`)
values (1,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,7,'danielfakolujo@gmail.com','Daniel','MALE','Fakolujo',6,'Oluwafolawemi','dandan2011','08096108342',2011,1),
(2,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,30,'seyifakolujo@gmail.com','Oluwaseyi','FEMALE','Fakolujo',10,'Michael','seyishow2020','08145327899',1985,2),
(3,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,10,'dolapoladipo@gmail.com','Dolapo','MALE','Ladipo',6,'Alex','dollypaton1245','08163783271',2000,1);

insert into customer_addresses(`customers_id`,`addresses_id`)
values (1,2),
(2,3),
(3,2);

insert into supplier(`id`,`company_name`,`date_supplier_created`,`date_supplier_updated`,`email_address`,`facility`,`nature_of_business`,`password`,`phone_number`,`rc_number`,`supplier_category`,`tax_identification_number`)
values(1,'Zero Waste Farms & Agro Business Limited',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,'zwfarms@gmail.com','OWN','Crop production and Animal Husbandry','zero0000@123','0816754789890','RC15689','PRODUCT_SUPPLIER','158298654417'),
(2,'Ope Farms Limited',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,'info@opefarms.com','OWN','Horticulture','opeoluwa@123#','08098765439','RC65431','PRODUCT_SUPPLIER','8764356781027'),
(3,'Xpart Tools',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,'info@xparttool.ng','LEASE','Plumbing, irrigation equipment and accessories supply','xpart@tool!','08023456719','RC45321','PRODUCT_SUPPLIER','865379646916');

insert into supplier_addresses(`suppliers_id`,`addresses_id`)
values(1,4),(2,5),(3,6);

insert into product(`id`,`product_category`,`product_colour`,`product_description`,`product_dimension`,`product_manufacturer`,`product_model`,`product_name`,`product_price`,`product_size`,`product_stock_quantity`,`product_style`,`product_unit_of_measure`,`product_weight`)
values(1,'VEGETABLE_CROPS','','Vegetable are green in nature and other comes in leave and fruits like they are source of vitamins','','','','Pepper',10000.00,'SMALL',100,'','BAG',50.00),
(2,'VEGETABLE_CROPS','','Vegetable are green in nature and other comes in leave and fruits like they are source of vitamins','','','','Tomatoes',10000.00,'SMALL',100,'','BAG',50.00),
(3,'VEGETABLE_CROPS','','Vegetable are green in nature and other comes in leave and fruits like they are source of vitamins','','','','Garden Egg',10000.00,'MEDIUM',100,'','BAG',50.00),
(4,'ACCESSORIES','Black','1000 ft, 36 inches emitter spacing, good for night shade plant irrigation','1/2 inch diameter x 10000 ft length','Hydro Flow Technologies','','Drip Tape',50000.00,'LARGE',1000,'','FT',5.0);

insert into supplier_products(`suppliers_id`,`products_id`)
values(1,1),
(1,2),
(2,3),
(3,4);


insert into customer_order(`id`,`delivery_date`,`order_date`,`shipping_date`,`customer_id`)
values(1,'2021-02-01',CURRENT_DATE,'2021-01-28',1),
(2,'2021-02-01',CURRENT_DATE,'2021-01-27',2),
(3,'2021-01-29',CURRENT_DATE,'2021-01-27',3);

insert into shipment(`id`,`shipping_charges`,`shipping_mode`,`customer_order_id`)
values(1,35000,'TRUCK',1),
(2,25000,'TRUCK',2),
(3,15000,'COURIER',3);

insert into order_item(`id`,`actual_delivery_date`,`order_quantity`,`order_status`,`value_added_tax`,`customer_order_id`)
values(1,'2021-02-03',50,'SHIPPED',0.07,1),
(2,'2021-02-01',100,'SHIPPED',0.07,1),
(3,'2021-02-01',100,'SHIPPED',0.07,1),
(4,'2021-02-04',100,'SHIPPED',0.07,2),
(5,'2021-01-29',3,'PREPARING_FOR_SHIPMENT',0.07,3);

insert into order_item_products(`order_items_id`,`products_id`)
values(1,1),
(2,2),
(3,3),
(4,3),
(5,4);






SET FOREIGN_KEY_CHECKS  = 1;