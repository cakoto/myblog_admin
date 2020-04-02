package com.gyf.myblog_admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan(basePackages = {"com.gyf.myblog_admin"})
@SpringBootApplication
public class MyblogAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyblogAdminApplication.class, args);
    }

}
