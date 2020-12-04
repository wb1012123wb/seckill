package com.wb.aop.aopConfig;

import com.wb.aop.aspect.serviceA.Audience2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;


@Component
@Configuration
@EnableAspectJAutoProxy
public class serviceAConfig {

    @Bean
    public Audience2 audience() {
        return new Audience2();
    }
}