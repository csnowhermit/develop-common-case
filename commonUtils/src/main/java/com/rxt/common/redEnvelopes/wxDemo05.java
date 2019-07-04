package com.rxt.common.redEnvelopes;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 微信红包Demo05：升级版-带上下限非对称平均值偏移修正随机红包算法
 * <p>
 * wxDemo04虽然相比wxDemo03，已经大大降低了对尾最小值出现的次数，但是仍然有一定数目最小值出现在队列末尾，
 * 这并不能让人满意。主要原因还是随机范围的不对称导致前面分配的红包平均值越来越大于理想平均值AVG，
 * 如果能在每一次红包分配时就尽量考虑将分配后的已发平均值往AVG靠，就能够尽可能减少对尾最小值出现的次数。
 * <p>
 * 当分配第n份红包时，
 * 已发红包平均值currAvg=sendedBonus/sendedNum，
 * 期望当前分配值exp=avg-(currAvg-avg)*sendedNum/(totalNum-sendedNum)，
 * 我们取exp和boundMax的中间值expMid=(exp+boundMax)/2，设置expMid~boundMax出现的概率为y，boundMin~boundMAx出现的概率为1-y，
 * 希望本次随机后期望值为exp，即exp=expMid*y+(boundMin+boundMax)/2*(1-y)，
 * 得出y=(exp-(boundMin+boundMax)/2)/(expMid-(boundMin+boundMax)/2)。
 */
public class wxDemo05 {

    /**
     * 返回一次抽奖在指定中奖概率下是否中奖
     *
     * @param rate 中奖概率
     * @return
     */
    public static boolean canReward(double rate) {
        return Math.random() <= rate;
    }

    /**
     * 返回min~max区间内随机数，含min和max
     *
     * @param min
     * @param max
     * @return
     */
    private static int getRandomVal(int min, int max) {
        Random random = new Random();
        return random.nextInt(Math.abs(max - min) + 1) + min;
    }

    /**
     * 带概率偏向的随机算法，概率偏向subMin~subMax区间
     * 返回boundMin~boundMax区间内随机数（含boundMin和boundMax），同时可以指定子区间subMin~subMax的优先概率
     * 例：传入参数(10, 50, 20, 30, 0.8)，则随机结果有80%概率从20~30中随机返回，有20%概率从10~50中随机返回
     *
     * @param boundMin 边界
     * @param boundMax
     * @param subMin
     * @param subMax
     * @param subRate
     * @return
     */
    public static int getRandomValWithSpecifySubRate(int boundMin, int boundMax, int subMin, int subMax, double subRate) {
        if (canReward(subRate)) {
            return getRandomVal(subMin, subMax);
        }
        return getRandomVal(boundMin, boundMax);
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
    private static Integer randomBonusWithSpecifyBound(Integer totalBonus, Integer totalNum, Integer sendedBonus,
                                                       Integer sendedNum, Integer rdMin, Integer rdMax) {
        Integer avg = totalBonus / totalNum;  // 平均值
        Integer leftLen = avg - rdMin;
        Integer rightLen = rdMax - avg;
        Integer boundMin = 0, boundMax = 0;

        // 大范围设置小概率
        if (leftLen.equals(rightLen)) {
            boundMin = Math.max((totalBonus - sendedBonus - (totalNum - sendedNum - 1) * rdMax), rdMin);
            boundMax = Math.min((totalBonus - sendedBonus - (totalNum - sendedNum - 1) * rdMin), rdMax);
        } else if (rightLen.compareTo(leftLen) > 0) {
            // 上限偏离
            double bigRate = leftLen / (double) (leftLen + rightLen);
            Integer standardRdMax = avg + leftLen;  // 右侧对称上限点
            Integer _rdMax = canReward(bigRate) ? rdMax : standardRdMax;
            boundMin = Math.max((totalBonus - sendedBonus - (totalNum - sendedNum - 1) * standardRdMax), rdMin);
            boundMax = Math.min((totalBonus - sendedBonus - (totalNum - sendedNum - 1) * rdMin), _rdMax);
        } else {
            // 下限偏离
            double smallRate = rightLen / (double) (leftLen + rightLen);
            Integer standardRdMin = avg - rightLen;  // 左侧对称下限点
            Integer _rdMin = canReward(smallRate) ? rdMin : standardRdMin;
            boundMin = Math.max((totalBonus - sendedBonus - (totalNum - sendedNum - 1) * rdMax), _rdMin);
            boundMax = Math.min((totalBonus - sendedBonus - (totalNum - sendedNum - 1) * standardRdMin), rdMax);
        }

        // 已发平均值偏移修正-动态比例
        if (boundMin.equals(boundMax)) {
            return getRandomVal(boundMin, boundMax);
        }
        double currAvg = sendedNum == 0 ? (double) avg : (sendedBonus / (double) sendedNum);  // 当前已发平均值
        double middle = (boundMin + boundMax) / 2.0;
        Integer subMin = boundMin, subMax = boundMax;
        // 期望值
        double exp = avg - (currAvg - avg) * sendedNum / (double) (totalNum - sendedNum);
        if (middle > exp) {
            subMax = (int) Math.round((boundMin + exp) / 2.0);
        } else {
            subMin = (int) Math.round((exp + boundMax) / 2.0);
        }
        Integer expBound = (boundMin + boundMax) / 2;
        Integer expSub = (subMin + subMax) / 2;
        double subRate = (exp - expBound) / (double) (expSub - expBound);
        return getRandomValWithSpecifySubRate(boundMin, boundMax, subMin, subMax, subRate);
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
     * @param bigRate     手动调整概率，使得波动数据概率大些
     * @return
     */
    private static Integer randomBonusWithSpecifyBound(Integer totalBonus, Integer totalNum, Integer sendedBonus,
                                                       Integer sendedNum, Integer rdMin, Integer rdMax, double bigRate) {
        Integer avg = totalBonus / totalNum;  // 平均值
        Integer leftLen = avg - rdMin;
        Integer rightLen = rdMax - avg;
        Integer boundMin = 0, boundMax = 0;

        // 大范围设置小概率
        if (leftLen.equals(rightLen)) {
            boundMin = Math.max((totalBonus - sendedBonus - (totalNum - sendedNum - 1) * rdMax), rdMin);
            boundMax = Math.min((totalBonus - sendedBonus - (totalNum - sendedNum - 1) * rdMin), rdMax);
        } else if (rightLen.compareTo(leftLen) > 0) {
            // 上限偏离
            Integer standardRdMax = avg + leftLen;  // 右侧对称上限点
            Integer _rdMax = canReward(bigRate) ? rdMax : standardRdMax;
            boundMin = Math.max((totalBonus - sendedBonus - (totalNum - sendedNum - 1) * standardRdMax), rdMin);
            boundMax = Math.min((totalBonus - sendedBonus - (totalNum - sendedNum - 1) * rdMin), _rdMax);
        } else {
            // 下限偏离
            Integer standardRdMin = avg - rightLen;  // 左侧对称下限点
            Integer _rdMin = canReward(bigRate) ? rdMin : standardRdMin;
            boundMin = Math.max((totalBonus - sendedBonus - (totalNum - sendedNum - 1) * rdMax), _rdMin);
            boundMax = Math.min((totalBonus - sendedBonus - (totalNum - sendedNum - 1) * standardRdMin), rdMax);
        }

        // 已发平均值偏移修正-动态比例
        if (boundMin.equals(boundMax)) {
            return getRandomVal(boundMin, boundMax);
        }
        double currAvg = sendedNum == 0 ? (double) avg : (sendedBonus / (double) sendedNum);  // 当前已发平均值
        double middle = (boundMin + boundMax) / 2.0;
        Integer subMin = boundMin, subMax = boundMax;
        // 期望值
        double exp = avg - (currAvg - avg) * sendedNum / (double) (totalNum - sendedNum);
        if (middle > exp) {
            subMax = (int) Math.round((boundMin + exp) / 2.0);
        } else {
            subMin = (int) Math.round((exp + boundMax) / 2.0);
        }
        Integer expBound = (boundMin + boundMax) / 2;
        Integer expSub = (subMin + subMax) / 2;
        double subRate = (exp - expBound) / (double) (expSub - expBound);
        return getRandomValWithSpecifySubRate(boundMin, boundMax, subMin, subMax, subRate);
    }


    /**
     * 生成红包一次分配结果
     *
     * @param totalBonus 总红包量
     * @param totalNum   总份数
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

    /**
     * 生成红包一次分配结果
     *
     * @param totalBonus
     * @param totalNum
     * @param rdMin
     * @param rdMax
     * @param bigRate    指定大范围区间的概率
     * @return
     */
    public static List<Integer> createBonusList(Integer totalBonus, Integer totalNum, Integer rdMin, Integer rdMax, double bigRate) {
        Integer sendedBonus = 0;
        Integer sendedNum = 0;
        List<Integer> bonusList = new ArrayList<>();
        while (sendedNum < totalNum) {
            Integer bonus = randomBonusWithSpecifyBound(totalBonus, totalNum, sendedBonus, sendedNum, rdMin, rdMax, bigRate);
            bonusList.add(bonus);
            sendedNum++;
            sendedBonus += bonus;
        }
        return bonusList;
    }

    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 100000000; i++) {
//            List<Integer> result = createBonusList(100, 12, 1, 20);
            List<Integer> result = createBonusList(100, 12, 1, 20, 0.8);

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
