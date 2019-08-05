package com.rxt.common.redEnvelopes;

import java.io.File;
import java.io.FileOutputStream;
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
public class RandomValue02 {
    /**
     * 返回min~max区间内随机数，含min和max
     *
     * @param min
     * @param max
     * @return
     */
    private static int getRandomVal(int min, int max) {
        Random random = new Random();
        int res = random.nextInt(Math.abs(max - min) + 1) + min;
//        return new Double(String.format("%.2f", res));
        return res;
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
    private static int randomBonusWithSpecifyBound(Integer totalBonus, Integer totalNum,
                                                   Integer sendedBonus, Integer sendedNum,
                                                   Integer rdMin, Integer rdMax) {
        int boundMin = Math.max((totalBonus - sendedBonus - (totalNum - sendedNum - 1) * rdMax), rdMin);
        int boundMax = Math.min((totalBonus - sendedBonus - (totalNum - sendedNum - 1) * rdMin), rdMax);
        return getRandomVal(boundMin, boundMax);
    }

    /**
     * 生成红包一次分配结果
     *
     * @param totalBonus 总红包量
     * @param totalNum   总份数
     * @return
     */
    public static List<Integer> createBonusList(Integer totalBonus, Integer totalNum) {
        Integer sendedBonus = 0;
        Integer sendedNum = 0;
        Integer rdMin = (int) (totalBonus / totalNum * 0.1);
        Integer rdMax = (int) (totalBonus / totalNum * 1.9);
        List<Integer> bonusList = new ArrayList<>();
        while (sendedNum < totalNum) {
            Integer bonus = randomBonusWithSpecifyBound(totalBonus, totalNum, sendedBonus, sendedNum, rdMin, rdMax);
            bonusList.add(bonus);
            sendedNum++;
            sendedBonus += bonus;
        }
        return bonusList;
    }

    public static void main(String[] args) throws Exception {
        FileOutputStream fileOutputStream = new FileOutputStream(new File("d:/weight.txt"));
        for (int i = 0; i < 10000; i++) {
            StringBuffer sb = new StringBuffer("");
            int max = 80;
            int min = 60;
            Integer pass_weight = new Integer(new Random().nextInt(Math.abs(max - min) + 1) + min);
            sb.append(pass_weight);    // 先保存
            sb.append(", ");

            List<Integer> result = createBonusList(new Integer(100 - pass_weight), new Integer(10));
            int total = 0;

            for (Integer r : result) {
//                if(r == 0.0){
//                    throw new Exception("随机数等于0" + result);
//                }
                total += r;
                sb.append(r);
                sb.append(", ");
            }
            sb.append("\n");
            System.out.println(sb.toString());
            fileOutputStream.write(sb.toString().getBytes());
//            System.out.println(result + " --> " + total);
        }
        fileOutputStream.close();
    }

}
