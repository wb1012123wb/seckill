package com.wb.annotation.testInherited;

import com.wb.annotation.MyAnnotation;

@MyAnnotation(name = "weibo", age = 27)
class Father {

    @Age(10)
    private int age;

}
