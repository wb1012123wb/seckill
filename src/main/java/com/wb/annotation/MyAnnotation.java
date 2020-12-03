package com.wb.annotation;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import java.lang.annotation.*;

/**
 * 自定义注解
 *
 * @Retention 注解存在阶段。
 * @Retention(RetentionPolicy.RUNTIME) 编译期。注解仅存在于源码中，在class字节码文件中不包含
 * @Retention(RetentionPolicy.RUNTIME) 类加载期。默认的保留策略，注解会在class字节码文件中存在，但运行时无法获得
 * @Retention(RetentionPolicy.RUNTIME) 运行期（JVM中运行）。注解会在class字节码文件中存在，在运行时可以通过反射获取到
 * <p>
 * @Target 注解的作用域
 * @Target(ElementType.TYPE) 作用于：接口、类、枚举、注解【常用】
 * @Target(ElementType.FIELD) 作用于：属性字段、枚举的常量
 * @Target(ElementType.METHOD) 作用于：方法
 * @Target(ElementType.PARAMETER) 作用于：方法参数
 * @Target(ElementType.CONSTRUCTOR) 作用于：构造函数
 * @Target(ElementType.LOCAL_VARIABLE) 作用于：局部变量
 * @Target(ElementType.ANNOTATION_TYPE) 作用于：注解
 * @Target(ElementType.PACKAGE) 作用于：包
 * @Target(ElementType.TYPE_PARAMETER) 作用于：类型泛型，即泛型方法、泛型类、泛型接口 （jdk1.8+）
 * @Target(ElementType.TYPE_USE) 类型使用。可以用于标注除了 class 的任意类型（jdk1.8+）
 * <p>
 * @Documented 能够将注解中的元素包含到 Javadoc 中去
 * <p>
 * @Inherited 如果该注解修饰了父类，子类没有被修饰，则子类可继承父类的该注解
 * <p>
 * @Repeatable 说明被这个元注解修饰的注解可以同时作用一个对象多次，但是每次作用注解又可以代表不同的含义。
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD, ElementType.FIELD, ElementType.CONSTRUCTOR})
@Documented
//@Inherited
//@Repeatable
//@Order(Ordered.HIGHEST_PRECEDENCE)
public @interface MyAnnotation {

    /**
     * 注解的属性相当于接口的方法，所以会有括号
     */

    /**
     * String
     */
    String name() default "";

    /**
     * int
     */
    int age() default 18;

}