package com.rxt.common.redEnvelopes;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 微信红包Demo02：带上下限对称随机红包算法
 * https://blog.csdn.net/paincupid/article/details/82054647
 * <p>
 * 假设总金币数量为S1，总份数为P1，已发币量为S2，已发份数为P2，则:
 * 平均值为S1/P1，则随机下限SMIN=(S1/P1)*0.1，随机上限SMAX=(S1/P1)*1.9。
 * 设生成的第n个随机币值为X，剩余获取总值为Y，则剩余币S1-S2=X+Y，当Y取最大值时X取最小值，当Y取最小值时X取最大值，同时X应处于限定随机上下限(SMIN~SMAX)之间，得出：
 * X的随机范围为：MAX(S1–S2-(P1–P2-1)*SMAX,SMIN)<=X<=MIN(S1–S2–(P1–P2-1)*SMIN,SMAX)
 */
public class wxDemo02 {
    /**
     * 返回min~max区间内随机数，含min和max
     *
     * @param min
     * @param max
     * @return
     */
    private static double getRandomVal(double min, double max) {
        Double res = (new Random().nextDouble()) * (max - min) + min;
        return new Double(String.format("%.2f", res));
//        return res;
    }

    /**
     * 随机分配第n个红包
     *
     * @param totalBonus  总红包量
     * @param totalNum    总份数
     * @param sendedBonus 已发送红包量
     * @param sendedNum   已发送份数
     * @param rdMin       随机下限
     * @param rdMax       随机上限
     * @return
     */
    private static Double randomBonusWithSpecifyBound(Integer totalBonus, Integer totalNum,
                                                      Double sendedBonus, Double sendedNum,
                                                      Double rdMin, Double rdMax) {
        Double boundMin = Math.max((totalBonus - sendedBonus - (totalNum - sendedNum - 1) * rdMax), rdMin);
        Double boundMax = Math.min((totalBonus - sendedBonus - (totalNum - sendedNum - 1) * rdMin), rdMax);
        return getRandomVal(boundMin, boundMax);
    }

    /**
     * 生成红包一次分配结果
     *
     * @param totalBonus 总红包量
     * @param totalNum   总份数
     * @return
     */
    public static List<Double> createBonusList(Integer totalBonus, Integer totalNum) {
        Double sendedBonus = 0.0;
        Double sendedNum = 0.0;
        Double rdMin = (double) (totalBonus / totalNum * 0.1);
        Double rdMax = (double) (totalBonus / totalNum * 0.9);
        List<Double> bonusList = new ArrayList<>();
        while (sendedNum < totalNum) {
            Double bonus = randomBonusWithSpecifyBound(totalBonus, totalNum, sendedBonus, sendedNum, rdMin, rdMax);
            bonusList.add(bonus);
            sendedNum++;
            sendedBonus += bonus;
        }
        return bonusList;
    }

    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 100000000; i++) {
            List<Double> result = createBonusList(new Integer(100), new Integer(12));
            Double total = 0.0;
            for (Double r : result) {
                if(r == 0.0){
                    throw new Exception("随机数等于0" + result);
                }
                total += r;
            }

            System.out.println(result + " --> " + total);
        }
    }
}
