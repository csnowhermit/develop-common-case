package com.rxt.common.utils;


import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

public class AutoSpeak {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        ActiveXComponent sap = new ActiveXComponent("Sapi.SpVoice");

        Dispatch sapo = sap.getObject();
        try {

            // 音量 0-100
            sap.setProperty("Volume", new Variant(100));
            // 语音朗读速度 -10 到 +10
            sap.setProperty("Rate", new Variant(2));


//            System.out.println("请输入要朗读的内容：");
//            Scanner scan=new Scanner(System.in);
//            String str=scan.next();

            //"夫何瑰逸之令姿，独旷世以秀群；表倾城之艳色，期有德于传闻。配明玉以比洁，齐幽兰以争芬；淡柔情于俗内，负雅致于高云。悲晨曦之易夕，感人生之长勤。",
            String[] strArr = {
                    "只走300米往右拐50米就到了",
                    "朝西直走到尽头",
                    "不客气，下一位",
                    "您买的票是C子头的，得到一楼候车"};
            for (String str : strArr) {
                // 执行朗读
                Dispatch.call(sapo, "Speak", new Variant(str));
                Thread.sleep(500);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sapo.safeRelease();
            sap.safeRelease();
        }
    }

}

