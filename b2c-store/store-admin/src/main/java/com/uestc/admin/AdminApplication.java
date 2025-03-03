package com.uestc.admin;

import com.uestc.clients.CategoryClient;
import com.uestc.clients.UserClient;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author llf
 * @school uestc
 */
@SpringBootApplication
@MapperScan(basePackages = "com.uestc.admin.mapper")
@EnableFeignClients(clients = {UserClient.class})
@EnableCaching
public class AdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class,args);
    }
}
