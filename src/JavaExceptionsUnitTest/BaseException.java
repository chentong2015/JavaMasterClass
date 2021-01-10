package JavaExceptionsUnitTest;

import java.util.InputMismatchException;
import java.util.Scanner;

// 处理程序异常的两种基本方式: LBYL & EAFP   ===> 具体分析; 选择使用
public class BaseException {

    private static int getIntFromConsole() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    // 1. 提供更好的直观性和错误的来源, 一般适用于可定义的/可预见的错误
    // 2. LBYL is needed when the failure is expected to happen but rarely.
    // 3. LBYL are not atomic 不是原子(操作)  !! TODO: 文件的判断和操作之间所存在的间隙，无法确定 !!
    private static int getIntLBYL() {
        Scanner scanner = new Scanner(System.in);
        boolean isValid = true;
        String inputStr = scanner.next();
        for (int i = 0; i < inputStr.length(); i++) {
            if (!Character.isDigit(inputStr.charAt(i))) {
                isValid = false;
                break;
            }
        }
        return isValid ? Integer.parseInt(inputStr) : 0;
    }

    // 1. EAFP is a good way to do validation
    // 2. 适用于IO操作, 确保操作过程的完成
    private static int getIntEAFP() {
        Scanner scanner = new Scanner(System.in);
        try {
            return scanner.nextInt();
        } catch (InputMismatchException exception) {
            return 0;
        }
    }
}
