/**
 * FileName: RandomLoadBanlance
 * Author:   Ren Xiaotian
 * Date:     2018/6/24 13:13
 */

package com.rxt.common.zk.loadBanlance;

import java.util.List;
import java.util.Random;

/**
 * 随机负载
 */
public class RandomLoadBanlance extends AbstractLoadBanlance {

    @Override
    protected String doSelect(List<String> repos) {
        int len = repos.size();
        Random random = new Random();

        return repos.get(random.nextInt(len));
    }
}
