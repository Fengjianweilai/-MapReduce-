package com.bigData.zjx.utils;
import java.io.*;
import java.util.List;

public class IoUtils{
    public static void WriteStringToFile(String filePath, List<String> data, String title){
        try {
            File file = new File(filePath);
            PrintStream ps = new PrintStream(new FileOutputStream(file));
            ps.println(title);//往文件写入字符串
            for(String d: data){
                ps.append(d);
            }
        } catch (FileNotFoundException e){
            e.printStackTrace();
        }
    }
}