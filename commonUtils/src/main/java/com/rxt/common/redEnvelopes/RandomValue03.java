package com.rxt.common.redEnvelopes;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 微信红包Demo03：升级版-带上下限非对称随机红包算法
 * https://blog.csdn.net/paincupid/article/details/82054647
 * <p>
 * 以上算法合理运行的前提是随机范围是对称的，SMIN~SMAX的平均值为期望的平均值，此时S1/P2=(SMIN+SMAX)/2。
 * 需求方希望在稳定的玩法上增加少许刺激，即允许让少数用户拿到较多数量的金币，此时S1/P2<(SMIN+SMAX)/2。
 * 此时使用上述算法仍然可以运行，每一份仍然可以分配到SMIN~SMAX之间的金币数量，并且总金币数能刚好被分完。
 */
public class RandomValue03 {
    /**
     * 返回min~max区间内随机数，含min和max
     *
     * @param min
     * @param max
     * @return
     */
    private static int getRandomVal(int min, int max) {
        Random random = new Random();
        int res = random.nextInt(Math.abs(max - min) + 1);
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
     * 生成红包一次分配结果，手动指定上下限
     *
     * @param totalBonus 总红包量
     * @param totalNum   总份数
     * @param rdMin      能拿到的最小金币数
     * @param rdMax      能拿到的最多金币数
     * @return
     */
    public static List<Integer> createBonusList(Integer totalBonus, Integer totalNum,
                                               Integer rdMin, Integer rdMax) {
        Integer sendedBonus = 0;
        Integer sendedNum = 0;
//        Double rdMin = (double) (totalBonus / totalNum * 0.1);
//        Double rdMax = (double) (totalBonus / totalNum * 0.9);
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
        for (int i = 0; i < 100000000; i++) {
            List<Integer> result = createBonusList(new Integer(100), new Integer(12), 1, 20);
            int total = 0;
            for (Integer r : result) {
//                if (r == 0.0) {
//                    throw new Exception("随机数等于0" + result);
//                }
                total += r;
            }

            System.out.println(result + " --> " + total);
        }
    }

}
