package JavaDateTime;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class JavaLocalDateTimeFormatter {

    // 根据自定义的格式输出DateTime时间
    public static void main(String[] args) {
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        System.out.println(localDateTime.format(formatter));
        System.out.println(formatter.format(localDateTime));
    }
}
