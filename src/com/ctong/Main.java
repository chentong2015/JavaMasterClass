package com.ctong;

import java.util.Calendar;
import java.util.Scanner;

// IDEA 配置问题  ===> idea会自动indexing扫描jdk中的工具包; idea需要沟通网络，实现工具的更新
// configure > settings > line number 显示出来
// (Zoom) 滚动放缩代码 change font-size with Ctrl+Mouse Wheel
// Config 01: Add unambiguous 明确的 imports on the fly
// Config 02: Optimise imports on the fly 移除不需要的import导入
// Config 03: 使用DiffMerge来比较代码版本的区别

public class Main {

    // 左边可以直接点击运行指定的main方法
    public static void main(String[] args) {

    }

    // Scanner 文本扫描器S
    // A simple text scanner which can parse primitive types and strings using regular expressions.
    private static void retrieveUserConsoleInput() {
        Scanner scanner = new Scanner(System.in); // Standard input stream => Keyboard input

        System.out.println("Input your year of birth");
        boolean isValidYear = scanner.hasNextInt(); // 判断输入的是有效值
        if (isValidYear) {
            int currentYear = Calendar.getInstance().get(Calendar.YEAR); // 使用Calendar拿到当前的年份
            int yearOfBirth = scanner.nextInt();  // 解析输入成int类型的值 ==> Throws Exception

            scanner.nextLine(); // Handle the next line data

            System.out.println("Enter your name:");
            String username = scanner.nextLine(); // read information form scanner ==> 安装Line行进行读取 !!
        } else {
            System.out.println("Uable to parse year of birth");
        }
        scanner.close();
    }

    private static int[] getIntegers(int count) {
        int[] values = new int[count];
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input " + count + " values \r");
        for (int i = 0; i < values.length; i++) {
            values[i] = scanner.nextInt();  // 确保输入的值都能被读取到, 有效的int值
        }
        return values;
    }

}
