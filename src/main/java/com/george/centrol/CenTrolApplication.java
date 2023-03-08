package com.george.centrol;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.george.centrol.mapper")
public class CenTrolApplication {

    public static void main(String[] args) {
        SpringApplication.run(CenTrolApplication.class, args);
    }

}
