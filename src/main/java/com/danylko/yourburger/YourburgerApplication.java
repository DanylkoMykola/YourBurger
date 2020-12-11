package com.danylko.yourburger;

import com.danylko.yourburger.config.StorageProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import java.io.IOException;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class YourburgerApplication {

    private static Logger logger = LoggerFactory.getLogger(YourburgerApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(YourburgerApplication.class, args);
    }

}
