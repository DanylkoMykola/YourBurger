DROP TABLE IF EXISTS PRODUCTS;

CREATE TABLE  PRODUCTS (
                         PROD_ID INT NOT NULL AUTO_INCREMENT,
                         NAME VARCHAR(40) NOT NULL,
                         IMAGE VARCHAR(255),
                         DESCRIPTION VARCHAR(1000),
                         PRICE INT NOT NULL,
                         VERSION INT NOT NULL,
                         PRIMARY KEY (PROD_ID)

);

DROP TABLE IF EXISTS CUSTOMERS;

CREATE TABLE CUSTOMERS (
                           CUST_ID INT NOT NULL AUTO_INCREMENT,
                           FIRST_NAME VARCHAR(40),
                           LAST_NAME VARCHAR(40),
                           PHONE_NUMBER VARCHAR(25),
                           EMAIL VARCHAR(255),
                           PASSWORD VARCHAR(255),
                           ROLE varchar(255),
                           STATUS varchar(255),
                           PRIMARY KEY  (CUST_ID)

);

DROP TABLE IF EXISTS ADDRESSES;

CREATE TABLE ADDRESSES (
                           ADDRESS_ID INT NOT NULL AUTO_INCREMENT,
                           CITY VARCHAR(40),
                           STREET VARCHAR(100),
                           STREET_NUMBER VARCHAR(15),
                           APARTMENT_NUMBER VARCHAR(5),
                           PRIMARY KEY (ADDRESS_ID)
);

DROP TABLE IF EXISTS ORDERS;

CREATE TABLE ORDERS (
                        ORDER_ID INT NOT NULL AUTO_INCREMENT,
                        CUST_ID INT,
                        ADDRESS_ID INT,
                        FAC_ID INT,
                        TOTAL_PRICE INT,
                        ORDER_DATE TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                        VERSION INT NOT NULL,
                        PRIMARY KEY (ORDER_ID),
                        FOREIGN KEY (CUST_ID) REFERENCES CUSTOMERS(CUST_ID),
                        FOREIGN KEY (ADDRESS_ID) REFERENCES ADDRESSES(ADDRESS_ID),
                        FOREIGN KEY (FAC_ID) REFERENCES FACILITIES(FAC_ID)

);

DROP TABLE IF EXISTS FACILITIES;

CREATE TABLE FACILITIES (
                            FAC_ID INT NOT NULL AUTO_INCREMENT,
                            FAC_CITY VARCHAR(30),
                            SERVING_CITY VARCHAR(30),
                            PRIMARY KEY (FAC_ID)
);

DROP TABLE IF EXISTS PRODUCT_LIST;

CREATE TABLE PRODUCT_LIST (
    PROD_LIST_ID INT NOT NULL,
    ORDER_ID INT NOT NULL,
    NAME INT NOT NULL,
    AMOUNT INT,
    FOREIGN KEY (ORDER_ID) REFERENCES ORDERS(ORDER_ID)

);

