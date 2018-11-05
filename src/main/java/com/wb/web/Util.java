package com.wb.wb;

public class Util {
    /*public static String strReverse(String str) {
        if (str.length() <= 1) {
            return str;
        }
        char[] charArray = str.toCharArray();
        StringBuilder strBuilder = new StringBuilder();
        for (int i = charArray.length - 1; i >= 0; i--) {
            strBuilder.append(charArray[i]);
        }
        return strBuilder.toString();
    }*/
    public static void main(String[] args) {

    }

    /**
     * @param initHight 初始高度
     * @param num 次数
     * @return
     */
    public static void fun (double initHight, int num) {
        int n = 0;
        double total = 0;
        double next = initHight / 2;
        for (n = 2; n <= num; n ++) {
            total += initHight + 2 * next;
            next /= 2;
        }
        // 反弹总长度
        System.out.println("total: " + total);
        // 最后一次的反弹高度
        System.out.println("last_1: " + next);
        // 最后一次的高度可以用算法解决：
        System.out.println("last_2" + initHight / (Math.pow(2, num)));;
    }
}
