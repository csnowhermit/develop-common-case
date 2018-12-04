/**
 * FileName: ElasticsearchApplicationTest
 * Author:   Ren Xiaotian
 * Date:     2018/12/4 19:45
 */

package com.rxt.common.es;

import com.rxt.common.es.config.MyPointConfig;
import com.rxt.common.es.model.SearchResult;
import com.rxt.common.es.service.NearbyService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ElasticsearchApplicationTest {
    @Autowired
    private NearbyService nearbyService;
    @Autowired
    private MyPointConfig myPointConfig;
    //这是我所在的坐标值，深圳

    private String myName = "Tom老师";//我的名字

    //	@Before
//	@Ignore
//	@Test
    public void initData() {
        int total = 100000;
        int inserted = 0;
        try {
            //建库、建表、建约束
            nearbyService.recreateIndex();
            //随机产生10W条数据
            inserted = nearbyService.addDataToIndex(myPointConfig.getLat(), myPointConfig.getLon(), total);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("\n========= 数据初始化工作完毕,共随机产生" + inserted + "条数据,失败(" + (total - inserted) + ")条 =========\n");
    }


    @Test
//	@Ignore
    public void searchNearby() {

        int size = 10, radius = 50;

        System.out.println("开始获取距离" + myName + radius + "米以内人");

        SearchResult result = nearbyService.search(myPointConfig.getLat(), myPointConfig.getLon(), radius, size, null);

        System.out.println("共找到" + result.getTotal() + "个人,优先显示" + size + "人，查询耗时" + result.getUseTime() + "秒");
        for (Map<String, Object> taxi : result.getData()) {

            String nickName = taxi.get("nickName").toString();

            String location = taxi.get("location").toString();
            Object geo = taxi.get("geoDistance");

            System.out.println(nickName + "，" +
                    "微信号:" + taxi.get("wxNo") +
                    "，性别:" + taxi.get("sex") +
                    ",距离" + myName + geo + "米" +
                    "(坐标：" + location + ")");
        }

        System.out.println("以上" + size + "人显示在列表中......");
        System.out.println("各位老司机们使出你们的撩妹技巧！！！");

    }

}
