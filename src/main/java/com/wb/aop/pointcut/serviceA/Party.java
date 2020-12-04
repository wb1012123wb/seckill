package com.wb.aop.pointcut.serviceA;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 晚会Party
 */
@Component
@Data
public class Party implements Performance {

    /**
     * 晚会主题
     */
    private String title;
    /**
     * 主持人
     */
    private String artist;
    /**
     * 节目单
     */
    private List<String> programList;


    @Override
    public void perform() {

    }

    @Override
    public void perform(String programmeId) {

    }

}
