import java.util.Calendar;
import java.util.Random;
import java.util.Scanner;

// TODO: Java基础知识点的补充 https://www.shuzhiduo.com/A/QV5ZLax2zy/
// Java开源库   https://howtodoinjava.com/java/library/

// IDEA背景设置 https://www.cnblogs.com/goodAndyxublog/p/14737271.html
// IDEA官方文档 https://www.jetbrains.com/help/idea/discover-intellij-idea.html#IntelliJ-IDEA-editions
// 1. IDEA会自动indexing扫描jdk中的工具包; idea需要沟通网络，实现工具的更新
// 2. configure > settings > line number 显示出来
// 3. change font-size with Ctrl+Mouse Wheel (Zoom) 滚动放缩代码
// 4. Add unambiguous 明确的 imports on the fly / Optimise imports on the fly 移除不需要的import导入
// 5. Open Module Settings 来添加.jar的类库和框架
// 6. Duplicate code 使IDEA自动的监测是否代码有重复
// 7. IDEA Terminal Git：Settings > Terminal > Shell Path > ...sh.exe --login -i 将终端的Shell显示在IDEA界面内部
// IDEA build软件，部署和发布
// 1. File > Project Structure > Artifacts > Create Jar Form Module > All Module + Main Class
// 2. META-INF的位置必须在主项目的目录下面
// 3. Build > Build Artifacts > Build

// 同一个文件中，只能有一个public的类型声明
public class Main {

    // Scanner 文本扫描器: A simple text scanner which can parse primitive types and strings using regular expressions
    private static void retrieveUserConsoleInput() {
        Scanner scanner = new Scanner(System.in); // Standard input stream => Keyboard input
        System.out.println("Input your year of birth");

        boolean isValidYear = scanner.hasNextInt(); // 判断输入的是有效值 !!
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

    private static void testRandomNumber() {
        Random random = new Random();
        int randomNum = random.nextInt(100); // 约束随机值的范围
    }
}
