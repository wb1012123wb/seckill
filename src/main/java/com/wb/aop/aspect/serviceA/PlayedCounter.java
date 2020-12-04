package com.wb.aop.aspect.serviceA;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import java.util.HashMap;
import java.util.Map;

/**
 * 对 Audience3 进行改进：处理通知中的参数
 * 切点方法所用表达式中的 perform 含参
 * <p>
 * 场景：节目可以点播，记录每一场节目的点播数
 */
@Aspect
public class PlayedCounter {

    /**
     * 点播数量
     */
    public Map<String, Integer> playedCountMap = new HashMap<>();

    /**
     * 切点
     * 不同点：声明了要提供给通知方法的参数
     * <p>
     * perform(String)：接收String类型的参数
     * args(programmeId)：指定参数，表明传递给perform方法的参数也会传递到通知当中。参数名称与切点方法签名中的参数名一致
     *
     * @param programmeId 节目ID
     */
    @Pointcut("execution(* com.wb.aop.pointcut.serviceA.Performance.perform(String))" +
            "&& args(programmeId)")
    public void performce(String programmeId) {
    }

    /**
     * 记录节目的点播数
     *
     * @param programmeId
     */
    @Before("performce(programmeId)")
    public void countPlayed(String programmeId) {
        int currentCount = getPlayCount(programmeId);
        playedCountMap.put(programmeId, currentCount + 1);
    }

    /**
     * 查询节目的点播数量
     *
     * @param programmeId 节目ID
     * @return
     */
    public int getPlayCount(String programmeId) {
        return playedCountMap.getOrDefault(programmeId, 0);
    }

}