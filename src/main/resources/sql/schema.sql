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
                           ADDRESS_ID INT NOT NULL,
                           FIRST_NAME VARCHAR(40),
                           LAST_NAME VARCHAR(40),
                           PHONE_NUMBER VARCHAR(30),
                           EMAIL VARCHAR(80),
                           PASSWORD VARCHAR(50),
                           RATING INT,
                           PRIMARY KEY  (CUST_ID),
                           FOREIGN KEY (ADDRESS_ID) REFERENCES ADDRESSES(ADDRESS_ID)
);

DROP TABLE IF EXISTS ADDRESSES;

CREATE TABLE ADDRESSES (
                           ADDRESS_ID INT NOT NULL AUTO_INCREMENT,
                           CITY VARCHAR(40),
                           STREET VARCHAR(60),
                           STREET_NUMBER VARCHAR(10),
                           APARTMENT_NUMBER VARCHAR(5),
                           PRIMARY KEY (ADDRESS_ID)
);

DROP TABLE IF EXISTS ORDERS;

CREATE TABLE ORDERS (
                        ORDER_ID INT NOT NULL AUTO_INCREMENT,
                        CUST_ID INT NOT NULL,
                        ADDRESS_ID INT NOT NULL,
                        FAC_ID INT NOT NULL,
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
    ORDER_ID INT NOT NULL,
    PROD_ID INT NOT NULL,
    AMOUNT INT,
    FOREIGN KEY (ORDER_ID) REFERENCES ORDERS(ORDER_ID),
    FOREIGN KEY (PROD_ID) REFERENCES PRODUCTS(PROD_ID)
);

