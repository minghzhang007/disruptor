package com.lewis.guava.util;

import com.google.common.base.Preconditions;

/**
 * Created by zhangminghua on 2017/3/1.
 */
public class PreconditionsDemo {
    public static void main(String[] args) {
        Integer number = 1;
        Integer integer = Preconditions.checkNotNull(number);
        System.out.println(integer);
        Preconditions.checkElementIndex(4,5,"元素越界了");
    }
}
