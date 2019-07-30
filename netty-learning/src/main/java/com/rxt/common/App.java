package com.rxt.common;

/**
 * Hello world!
 */
public class App {

    public Integer IntegerDemo(){
        Integer i = 0;
        try{
            return i;
        }finally {
            i = 10;
            System.out.println("done");
        }
    }

    public int intDemo(){
        int i = 0;
        try{
            return i;
        }finally {
            i = 10;
            System.out.println("done");
        }
    }

    public static void main(String[] args) {
        System.out.println(new App().IntegerDemo());
        System.out.println(new App().intDemo());
    }
}
