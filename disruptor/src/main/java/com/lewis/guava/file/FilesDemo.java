package com.lewis.guava.file;

import com.google.common.io.Files;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

/**
 * Created by zhangminghua on 2017/3/1.
 */
public class FilesDemo {
    public static void main(String[] args) throws IOException {
        String path ="D:\\hello.txt";
        File file = new File(path);
        Files.write("hello ! my name is lewis !",file,Charset.forName("utf-8"));
    }
}
