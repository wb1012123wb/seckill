package com.wb.annotation.bankServer;

import java.lang.reflect.Method;

/**
 * 转账处理业务类
 */
public class BankService {

    /**
     * @param money 转账金额
     */
    @BankTransferMoneyCheck(maxMoney = 15000)
    public static void transferMoney(double money) {
        System.out.println(processAnnotationMoney(money));
    }

    private static String processAnnotationMoney(double transferMoney) {
        try {
            Method transferMoneyMethod = BankService.class.getDeclaredMethod("transferMoney", double.class);
            boolean annotationPresent = transferMoneyMethod.isAnnotationPresent(BankTransferMoneyCheck.class);// transferMoney() 是否被 @BankTransferMoney 标注
            if (annotationPresent) {
                BankTransferMoneyCheck annotation = transferMoneyMethod.getAnnotation(BankTransferMoneyCheck.class);
                double maxMoney = annotation.maxMoney();
                if (transferMoney > maxMoney) {
                    return "转账金额大于限额，不能转账";
                } else {
                    return "转账金额为:" + transferMoney + "，可以转账";
                }
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return "转账校验失败";
    }

    public static void main(String[] args) {
        transferMoney(1000);
    }

}