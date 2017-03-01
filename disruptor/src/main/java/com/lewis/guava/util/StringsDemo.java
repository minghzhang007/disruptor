package com.lewis.guava.util;

import com.google.common.base.Strings;

/**
 * Created by zhangminghua on 2017/3/1.
 */
public class StringsDemo {
    public static void main(String[] args) {
        String s = Strings.padStart("hello the world !", 10, '_');
        System.out.println(s);

        String hello = Strings.padEnd("hello", 10, '!');
        System.out.println(hello);

        String h1 = Strings.repeat("hello!!!!!", 5);
        System.out.println(h1);
        String s1 = Strings.commonPrefix("中国人民银行", "中国人啊");
        System.out.println(s1);
    }
}
