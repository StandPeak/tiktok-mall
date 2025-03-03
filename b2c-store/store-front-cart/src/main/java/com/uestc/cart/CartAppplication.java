package com.uestc.cart;

import com.uestc.clients.ProductClient;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author llf
 * @school uestc
 */
@SpringBootApplication
@MapperScan("com.uestc.cart.mapper")
@EnableFeignClients(clients = {ProductClient.class})
public class CartAppplication {

    public static void main(String[] args) {
        SpringApplication.run(CartAppplication.class, args);
    }
}
