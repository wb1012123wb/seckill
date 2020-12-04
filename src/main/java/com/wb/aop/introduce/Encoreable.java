package com.wb.aop.introduce;

/**
 * 我们需要有一种方式将这个接口应用到 Performance 所有实现中。
 * 但是并不是所有的 Performance 实现都具有 Encoreable 特性，
 * 同时，可能无法修改所有的 Performance 实现，使用第三方实现且没有源码的时候更是如此。
 *
 * <p>解决：</p>
 * 借助于 AOP 的引入功能，我们可以不必在设计上妥协或者侵入性地改变现有的实现。
 * 为了实现该功能，我们要创建一个新的切面：EncodeableIntroducer
 */
public interface Encoreable {
    void performEncore();
}