package com.ugc.micropayment.configuration;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by fanjl on 2017/4/12.
 */
@Configuration
@EnableAutoConfiguration
@EntityScan(basePackages = {"com.ugc.micropayment.domain","com.ugc.micropayment.config"})
@ComponentScan({"com.ugc.micropayment.service"})
@MapperScan("com.ugc.micropayment.mapper")
public class ConfigurationTest {

}
