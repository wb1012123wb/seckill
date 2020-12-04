package com.wb.aop.aspect.serviceB;

import com.wb.aop.pointcut.serviceA.AnnotationPointCut;

public class Temp {

    @AnnotationPointCut
    public void temp() {
        System.out.println("处理完切面后，处理业务...");
    }

}
