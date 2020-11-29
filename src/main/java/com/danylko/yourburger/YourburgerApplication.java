package com.danylko.yourburger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class YourburgerApplication {

    private static Logger log = LoggerFactory.getLogger(YourburgerApplication.class);

    public static void main(String[] args) throws IOException {
        SpringApplication.run(YourburgerApplication.class, args);

    }

}
