package com.wb.aop.aspect.serviceA;

import com.wb.aop.aopConfig.ProgramCounterConfig;
import com.wb.aop.aspect.serviceB.Temp;
import com.wb.aop.pointcut.serviceA.AnnotationPointCut;
import com.wb.aop.pointcut.serviceA.Performance;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ProgramCounterConfig.class)
public class PlayedCounterTestAnnotation {

    @Autowired
    private Performance performance;

    @Autowired
    private PlayedCounter counter;

//    @Autowired
//    private AnnotatePlayedCounter annotatePlayedCounter;

    @Autowired
    private Temp temp;

    @AnnotationPointCut
    public void performance() {
    }

    @Test
    public void playedCounter() {
        performance.perform("节目1");
        performance.perform("节目1");
        performance.perform("节目2");
        performance.perform("节目2");
        performance.perform("节目2");
        performance.perform("节目3");
        performance.perform("节目4");

        System.out.println(counter.playedCountMap.toString());

        assertEquals(2, counter.getPlayCount("节目1"));
        assertEquals(3, counter.getPlayCount("节目2"));
        assertEquals(1, counter.getPlayCount("节目3"));
        assertEquals(1, counter.getPlayCount("节目4"));


        /**
         * 测试注解切点
         */
        temp.temp();
        System.out.println("AnnotatePlayedCounter.playedCount === " + AnnotatePlayedCounter.getPlayCount());
    }
}