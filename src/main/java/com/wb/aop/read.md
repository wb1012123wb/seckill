依赖：
    <dependency><!-- aspectjweaver 包含 aspectjrt -->
        <groupId>org.aspectj</groupId>
        <artifactId>aspectjweaver</artifactId>
        <version>1.9.6</version>
    </dependency>
    <dependency>
        <groupId>org.sonatype.sisu</groupId>
        <artifactId>sisu-inject-bean</artifactId>
        <version>1.4.2</version>
    </dependency>


pointcut：切点
aspect：  切面


通知【advice】：切面是什么以及何时使用。
连接点【join point】：使用通知的时机。
切点【pointcut】：切哪里。
切面【Aspect】：是什么，在何时和何处完成其功能。
引入【Introduction】：
织入【Weaving】：


Spring 切面可以应用 5 种类型的通知：
    前置通知（Before）：在目标方法被调用之前调用通知功能；
    后置通知（After）：在目标方法完成之后调用通知，此时不会关心方法的输出是什么；
    返回通知（After-returning）：在目标方法成功执行之后调用通 知；
    异常通知（After-throwing）：在目标方法抛出异常后调用通知；
    环绕通知（Around）：通知包裹了被通知的方法，在被通知的方法调用之前和调用之后执行自定义的行为。

Spring 提供了 4 种类型的 AOP 支持：
    基于代理的经典 Spring AOP；
    纯 POJO 切面；
    @AspectJ 注解驱动的切面；
    注入式 AspectJ 切面（适用于 Spring 各版本）。
前三种都是 Spring AOP 实现的变体，Spring AOP 构建在动态代理基础之上，因此，Spring 对 AOP 的支持局限于方法拦截。


