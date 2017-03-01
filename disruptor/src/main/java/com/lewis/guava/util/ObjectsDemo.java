package com.lewis.guava.util;

import com.google.common.base.Objects;

/**
 * Created by zhangminghua on 2017/3/1.
 */
public class ObjectsDemo {
    public static void main(String[] args) {
        boolean equal = Objects.equal(null, null);
        System.out.println(equal);
    }
}
