package com.uestc.carousel;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;

/**
 * @author llf
 * @school uestc
 */
@SpringBootApplication
@MapperScan(basePackages = "com.uestc.carousel.mapper")
@EnableCaching
public class CarouselApplication {

    public static void main(String[] args) {
        SpringApplication.run(CarouselApplication.class, args);
    }
}
