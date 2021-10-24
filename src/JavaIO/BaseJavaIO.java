package JavaIO;

import java.util.Calendar;
import java.util.Scanner;

// TODO: IO作用在Streams (bytes & binary)，数据的读取可以是一次性的或者buffered
// The source and destination of I/O
//    1. Files on the PC disk drives 磁盘数据 / SSD固态硬盘数据 / 机械硬盘写入方式是覆盖
//    2. Networking Stream
//    3. Pips
//    4. Web Socket
//    5. Computer's keyboard and screen
// Format:
//    1. binary format, XML, JSON
//    2. Serial & Sequential files: A Steam of data, each piece of data following in sequence
//    3. Random access files 随机访问和修改(位置)上的数据
// TODO: 如何选择Java.io & Java.nio
// 1. io一般用于文件的读写和streams流的操作(write写到OS内存，flush将数据冲到磁盘)
// 2. nio一般使用在文件系统的操作上面
public class BaseJavaIO {

    /**
     * java.io.File 类型的问题
     * 1. File.delete() 没有异常的抛出，也没有具体的错误原因
     * 2. File.rename() 在不同的平台结果不同
     * 3. No support for symbolic links
     * 4. Cannot get metadata about a file 不能获取文件权限和安全信息
     * 5. Don't perform well when working with lots of data
     */

    // java.noi.File 该类型解决了java.io.File类型所出现的问题
    // java.nio.file.Files类型包含处理系统文件的基本方法

    /**
     * TODO: NIO基于通道Channel和缓冲区Buffer的I/O方式, 处理的是块数据block of data
     *       NOI Non-blocking 非阻塞, 有助于性能的提升
     * 1. Path + Files + "Java.io.*": BufferedWriter/BufferedReader; ObjectInputStream/ObjectOutputStream
     * 2. File Channel + ByteBuffer : 调用Channel read() & write()方法
     * 3. Path + Files + File Systems文件系统交互
     */

    // Scanner 文本扫描器:
    // A simple text scanner which can parse primitive types and strings using regular expressions
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
}
