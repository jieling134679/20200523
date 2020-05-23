package com;

/*
    人行日志不允许出现一些关键词，手工替换很麻烦
    1. 文件1，存放关键词的文件，关键词组对用竖线分隔，#前面的词替换#后面的词
             例:  错误#非正确|失败#非成功|error#ERR
    2. 将需要替换关键词的文件放在文件夹中
    3. 一键批量处理，将处理好的文件导出到另一个文件夹中
    author : likx
    date : 20200521
    version : 2.0
 */

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class WordsReplaceBatch {
    static String inputPath = System.getProperty("user.dir") + "\\需要替换的文件放这里";
    static String outputPath = System.getProperty("user.dir") + "\\替换后的文件放这里";
    static String replaceFilePath = System.getProperty("user.dir") + "\\需要替换的词.txt";

    //获取文件夹下所有文件的文件名
    public static void batchReplace() {
        String content;
        File file = new File(inputPath);
        String replacePair = WordsReplace.read(replaceFilePath);
//        System.out.println("replacePair = " + replacePair);

        File[] files = file.listFiles();
        for (File f : files) {
            System.out.println("读取文件路径 : " + inputPath + "\\" + f.getName());
            //读取文件内容
            content = WordsReplace.read(inputPath + "\\" + f.getName());
            System.out.println("替换前文件内容 : " + content);
            System.out.println("读取文件结束，开始替换");
            content = WordsReplace.replace(replacePair, content);
            System.out.println("替换文件结束");
            System.out.println("替换后文件内容 : " + content);
            System.out.println("输出文件路径 : " + outputPath + "\\" + f.getName());
            System.out.println();
            write(content, outputPath + "\\" + f.getName());
        }
    }

    public static void write(String content, String outputPath) {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(
                    new FileWriter(outputPath));
            bufferedWriter.write(content);
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        batchReplace();
    }

}
