import java.util.Scanner;

// Scanner: 接受指定IO数据，以指定的格式获取数据
public class JavaUtilScanner {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input your year of birth");
        boolean isValidYear = scanner.hasNextInt();
        if (isValidYear) {
            int yearOfBirth = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Enter your name:");
            String name = scanner.nextLine();

            System.out.println(yearOfBirth);
            System.out.println(name);
        } else {
            System.out.println("Invalid year of birth");
        }
        // 调用.close()方法时可能抛出异常
        scanner.close();
    }
}
