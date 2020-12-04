package com.wb.aop.aopConfig;

import com.wb.aop.aspect.serviceA.AnnotatePlayedCounter;
import com.wb.aop.aspect.serviceA.PlayedCounter;
import com.wb.aop.aspect.serviceB.Temp;
import com.wb.aop.pointcut.serviceA.Party;
import com.wb.aop.pointcut.serviceA.Performance;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;


@Configuration
@EnableAspectJAutoProxy
public class ProgramCounterConfig {

    @Bean
    public Performance partyInfo() {
        return new Party();
    }

    @Bean
    public PlayedCounter playedCounter() {
        return new PlayedCounter();
    }

    @Bean
    public AnnotatePlayedCounter AnnotatePlayedCounter() {
        return new AnnotatePlayedCounter();
    }

    @Bean
    public Temp temp() {
        return new Temp();
    }
}