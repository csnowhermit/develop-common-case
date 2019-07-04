package com.rxt.common.redEnvelopes;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 微信红包Demo04：升级版非对称红包分配算法
 */
public class wxDemo04 {
    /**
     * 返回一次抽奖在指定中奖概率下是否中奖
     * @param rate 中奖概率
     * @return
     */
    public static boolean canReward(double rate) {
        return Math.random() <= rate;
    }

    /**
     * 返回min~max区间内随机数，含min和max
     * @param min
     * @param max
     * @return
     */
    private static int getRandomVal(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }

    /**
     * 随机分配第n个红包
     * @param totalBonus 总红包量
     * @param totalNum 总份数
     * @param sendedBonus 已发送红包量
     * @param sendedNum 已发送份数
     * @param rdMin 随机下限
     * @param rdMax 随机上限
     * @return
     */
    private static Integer randomBonusWithSpecifyBound(Integer totalBonus, Integer totalNum, Integer sendedBonus,
                                                       Integer sendedNum, Integer rdMin, Integer rdMax) {
        Integer avg = totalBonus / totalNum;
        Integer leftLen = avg - rdMin;
        Integer rightLen = rdMax - avg;
        Integer boundMin = 0, boundMax = 0;
        if (leftLen.equals(rightLen)) {
            boundMin = Math.max((totalBonus - sendedBonus - (totalNum - sendedNum - 1) * rdMax), rdMin);
            boundMax = Math.min((totalBonus - sendedBonus - (totalNum - sendedNum - 1) * rdMin), rdMax);
        } else if (rightLen.compareTo(leftLen) > 0) {
            // 上限偏离
            double bigRate = leftLen / (double)(leftLen + rightLen);
            Integer standardRdMax = avg + leftLen;  // 右侧对称上限点
            Integer _rdMax = canReward(bigRate) ? rdMax : standardRdMax;
            boundMin = Math.max((totalBonus - sendedBonus - (totalNum - sendedNum - 1) * standardRdMax), rdMin);
            boundMax = Math.min((totalBonus - sendedBonus - (totalNum - sendedNum - 1) * rdMin), _rdMax);
        } else {
            // 下限偏离
            double smallRate = rightLen / (double)(leftLen + rightLen);
            Integer standardRdMin = avg - rightLen;  // 左侧对称下限点
            Integer _rdMin = canReward(smallRate) ? rdMin : standardRdMin;
            boundMin = Math.max((totalBonus - sendedBonus - (totalNum - sendedNum - 1) * rdMax), _rdMin);
            boundMax = Math.min((totalBonus - sendedBonus - (totalNum - sendedNum - 1) * standardRdMin), rdMax);
        }
        return getRandomVal(boundMin, boundMax);
    }

    /**
     * 生成红包一次分配结果
     * @param totalBonus 总红包量
     * @param totalNum 总份数
     * @return
     */
    public static List<Integer> createBonusList(Integer totalBonus, Integer totalNum, Integer rdMin, Integer rdMax) {
        Integer sendedBonus = 0;
        Integer sendedNum = 0;
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
            List<Integer> result = createBonusList(100, 12, 1, 20);
            Integer total = 0;
            for (Integer r : result) {
                if (r == 0.0) {
                    throw new Exception("随机数等于0" + result);
                }
                total += r;
            }

            System.out.println(result + " --> " + total);
        }
    }

}
