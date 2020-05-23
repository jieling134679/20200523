package com;

/*
    人行日志不允许出现一些关键词，手工替换很麻烦
    输入:
        1. 文件1，存放关键词的文件，关键词组对用竖线分隔，#前面的词替换#后面的词
             例:  错误#非正确|失败#非成功|error#ERR
        2. 文件2，要替换关键词的日志文件
    输出:
        1.替换过关键词的新文件，文件名前加"日志_"  例: 原文件名"20200521.log"   新文件名"日志_20200521.log"
    author : likx
    date : 20200521
    version : 1.0
 */

import java.io.*;
import java.util.Date;

public class WordsReplace {
    static String encoding = "GB2312";
    static String contextPath = System.getProperty("user.dir");

    //输入文件路径filepath，输出文件内容
    public static String read(String filepath) {

        String content = "", s;
        File file = new File(filepath);
        if (file.isFile() && file.exists()) {
            try {
//                System.out.println("读取开始时间" + new Date().getTime());
                FileInputStream fileInputStream = new FileInputStream(file);
                InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, encoding);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
//                System.out.println("BufferdReader读取结束时间" + new Date().getTime());
                StringBuilder stringBuilder = new StringBuilder();
                while ((s = bufferedReader.readLine()) != null) {
                    stringBuilder.append(s + "\n");
//                  content += (s + "\n");
                }
                content = stringBuilder.toString();
//                System.out.println("BufferdReader每一行读取结束时间" + new Date().getTime());
                bufferedReader.close();
                inputStreamReader.close();
                fileInputStream.close();
                //末尾会多一个\n
                if (!("".equals(content))) {
                    content = content.substring(0, content.length() - 1);
                }
//                System.out.println(content);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("找不到指定的文件");
        }
        return content;
    }

    public static String replace(String content1, String content2) {
        String[] words = content1.split("\\|");
        for (String word : words) {
            if (!("".equals(word)) && word.contains("#")) {
                String[] pair = word.split("#");
                if (!("".equals(pair[0]))) {
                    content2 = content2.replaceAll(pair[0], pair[1]);
                }
            }
        }
        return content2;
    }

    public static void write(String content) {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(
                    new FileWriter(contextPath + "\\替换后的日志文件.log"));
            bufferedWriter.write(content);
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

//    public static void main(String[] args){
//        //获取系统路径
//        String content1 = read(contextPath + "\\需要替换的词.txt");
//        String content2 = read(contextPath + "\\需要替换的日志放这里面.log");
//
//        System.out.println("替换后 : " + replace(content1, content2));
//        content2 = replace(content1, content2);
//        write(content2);
//    }
}


