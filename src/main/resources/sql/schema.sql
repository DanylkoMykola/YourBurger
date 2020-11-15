DROP TABLE IF EXISTS BURGERS;

CREATE TABLE BURGERS (
                         BURID INT NOT NULL AUTO_INCREMENT,
                         NAME VARCHAR(40) NOT NULL,
                         IMAGE BLOB,
                         DESCRIPTION VARCHAR(1000),
                         PRICE INT NOT NULL,
                         PRIMARY KEY (BURID)
);

DROP TABLE IF EXISTS CUSTOMERS;

CREATE TABLE CUSTOMERS (
                           CUSTID INT NOT NULL AUTO_INCREMENT,
                           ADDRESSID INT NOT NULL,
                           FIRSTNAME VARCHAR(40),
                           SURNAME VARCHAR(40),
                           PHONENUMBER VARCHAR(30),
                           EMAIL VARCHAR(80),
                           PASSWORD VARCHAR(50),
                           RATING INT,
                           PRIMARY KEY  (CUSTID),
                           FOREIGN KEY (ADDRESSID) REFERENCES ADDRESSES(ADDRESSID)
);

DROP TABLE IF EXISTS ADDRESSES;

CREATE TABLE ADDRESSES (
                           ADDRESSID INT NOT NULL AUTO_INCREMENT,
                           CITY VARCHAR(30),
                           STREET VARCHAR(60),
                           STREETNUMBER VARCHAR(10),
                           APARTMENTNUMBER VARCHAR(5),
                           PRIMARY KEY (ADDRESSID)
);

DROP TABLE IF EXISTS ORDES;

CREATE TABLE ORDERS (
                        ORDERID INT NOT NULL AUTO_INCREMENT,
                        CUSTID INT NOT NULL,
                        ADDRESSID INT NOT NULL,
                        FACID INT NOT NULL,
                        TOTALPRICE INT,
                        ORDERDATE TIMESTAMP,
                        PRIMARY KEY (ORDERID),
                        FOREIGN KEY (CUSTID) REFERENCES CUSTOMERS(CUSTID),
                        FOREIGN KEY (ADDRESSID) REFERENCES ADDRESSES(ADDRESSID),
                        FOREIGN KEY (FACID) REFERENCES FACILITIES(FACID)
);

DROP TABLE IF EXISTS FACILITIES;

CREATE TABLE FACILITIES (
                            FACID INT NOT NULL AUTO_INCREMENT,
                            FACCITY VARCHAR(30),
                            SERVCITYID INT NOT NULL,
                            PRIMARY KEY (FACID),
                            FOREIGN KEY (SERVCITYID) REFERENCES SERVICECITY(SERCITYID)
);

DROP TABLE IF EXISTS SERVICECITY;

CREATE TABLE  SERVICECITY (
                              SERCITYID INT NOT NULL AUTO_INCREMENT,
                              CITY VARCHAR(30),
                              PRIMARY KEY  (SERCITYID)
);