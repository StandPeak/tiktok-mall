package com.uestc.product.config;

import com.uestc.config.CacheConfiguration;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author llf
 * @school uestc
 */
@Configuration
public class ProductConfiguration extends CacheConfiguration {

    /**
     * mq序列化方式，选择json!
     * @return
     */
    @Bean
    public MessageConverter messageConverter()
    {
        return new Jackson2JsonMessageConverter();
    }
}
