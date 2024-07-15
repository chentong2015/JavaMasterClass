package jvm_runtime_api;

import java.io.File;
import java.io.IOException;

public class JavaRuntimeExec {

    public static void main(String[] args) throws IOException, InterruptedException {
        String[] cmd1 = {"mkdir", "/Users/tongchen/Desktop/testFolder"};
        Process process = Runtime.getRuntime().exec(cmd1);
        System.out.println("Exit Code: " + process.waitFor());

        String[] cmd2 = {"ls", "-l", "/Users/tongchen"};
        Process process1 = Runtime.getRuntime().exec(cmd2);
        System.out.println("Exit Code: " + process1.waitFor());
    }

    private static void testRuntimeExec() throws IOException, InterruptedException {
        // Executes the "notepad.exe" command, which opens Notepad.
        // Process process1 = Runtime.getRuntime().exec("notepad.exe");

        String[] cmd2 = {"echo", "$HOME"};
        Process process2 = Runtime.getRuntime().exec(cmd2);
        System.out.println("Exit Code: " +  process2.waitFor());

        // 执行命令的数组，提供环境变量参数
        // Executes the "echo $HOME" command with a custom environment variable.
        String[] cmd3 = {"echo", "$HOME"};
        String[] env3 = {"HOME=/path/to/custom/home"};
        Process process3 = Runtime.getRuntime().exec(cmd3, env3);
        System.out.println("Variant 3 - Exit Code: " +  process3.waitFor());

        // Executes the "ls -l" command with a custom environment variable and working directory.
        String[] cmd4 = {"ls", "-l"};
        String[] env4 = {"HOME=/path/to/custom/home"};
        File workingDir4 = new File("/path/to/directory");
        Process process4 = Runtime.getRuntime().exec(cmd4, env4, workingDir4);
        System.out.println("Variant 4 - Exit Code: " + process4.waitFor());

        // Executes the "echo $HOME" command with a custom environment variable.
        String cmd5 = "echo $HOME";
        String[] env5 = {"HOME=/path/to/custom/home"};
        Process process5 = Runtime.getRuntime().exec(cmd5, env5);
        System.out.println("Variant 5 - Exit Code: " + process5.waitFor());

        // Variant 6: exec(String command, String[] envp, File dir)
        // Executes the "ls -l" command with a custom environment variable and working directory.
        String cmd6 = "ls -l";
        String[] env6 = {"HOME=/path/to/custom/home"};
        File workingDir6 = new File("/path/to/directory");
        Process process6 = Runtime.getRuntime().exec(cmd6, env6, workingDir6);
        System.out.println("Variant 6 - Exit Code: " + process6.waitFor());
    }
}
