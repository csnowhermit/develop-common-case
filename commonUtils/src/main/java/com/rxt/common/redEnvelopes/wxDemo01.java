package com.rxt.common.redEnvelopes;

import java.util.Random;

/**
 * 微信红包算法
 * https://blog.csdn.net/paincupid/article/details/82054647
 */
public class wxDemo01 {

    /**
     * 生成随机的钱数
     *
     * @param _leftMoneyPackage
     * @return
     */
    public static double getRandomMoney(LeftMoneyPackage _leftMoneyPackage) {
        // remainSize 剩余的红包数量
        // remainMoney 剩余的钱
        if (_leftMoneyPackage.remainSize == 1) {
            _leftMoneyPackage.remainSize--;
            return (double) Math.round(_leftMoneyPackage.remainMoney * 100) / 100;
        }
        Random r = new Random();
        double min = 0.01; //
        double max = _leftMoneyPackage.remainMoney / _leftMoneyPackage.remainSize * 2;
        if (max > 6) {
            max = 6;
        }
        double money = r.nextDouble() * max;
        money = money <= min ? 0.01 : money;
        money = Math.floor(money * 100) / 100;
        _leftMoneyPackage.remainSize--;
        _leftMoneyPackage.remainMoney -= money;
        return money;
    }

    public static void main(String[] args) {
        LeftMoneyPackage moneyPackage = new LeftMoneyPackage();
        moneyPackage.remainMoney = 40;
        moneyPackage.remainSize = 10;

        double total=0;
        while (moneyPackage.remainSize != 0) {
            double now = getRandomMoney(moneyPackage);
            total += now;
            System.out.print(now + ", ");
        }
        System.out.println(" ==> " + total);
    }

}

class LeftMoneyPackage{
    public static int remainMoney;    // 剩余钱
    public static int remainSize;     // 剩余红包数量
}


