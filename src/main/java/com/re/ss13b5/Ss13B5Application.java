package com.re.ss13b5;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.autoconfigure.DataSourceAutoConfiguration;


// FIX: Loại bỏ các cấu hình tự động của JDBC và JPA để dùng Hibernate Config của mình
@SpringBootApplication(exclude = {
        DataSourceAutoConfiguration.class})
public class Ss13B5Application {
    public static void main(String[] args) {
        SpringApplication.run(Ss13B5Application.class, args);
    }
}