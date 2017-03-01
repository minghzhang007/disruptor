package com.lewis.disruptor.demo1;

import java.util.Random;

/**
 * Created by zhangminghua on 2017/3/1.
 */
public class RandomUtil {

    private static final char[] chars = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z',
                                'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z',
                                '0','1','2','3','4','5','6','7','8','9'};

    private static Random random = new Random();

    public static char getChar(){
        char aChar = chars[random.nextInt(62)];
        return aChar;
    }


}
