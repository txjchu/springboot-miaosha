package com.miaoshaproject;

import org.junit.Test;

/**
 * @author: Peter
 * @date: 2020/12/25 19:47
 */
public class DemoTest {

    @Test
    public void t0(){
        String a = "Hello World";
        String b = "";
        // b = a - "Hello"; // 不可以，会编译错误
        System.out.println(b);
    }
}
