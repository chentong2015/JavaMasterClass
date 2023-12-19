package JavaIOResources.resources;

import java.io.*;

// 涉及一个抽象类，支持从纯Filepath文件路径和Classpath路径读取文件
public abstract class JavaFileBasedSourceLoader {

    // 在抽象类实现的过程中确定文件的来源，并提供正常的文件路径
    protected abstract boolean isResourceInClassPath();

    protected abstract String getFilePath();

    // 在抽象父类中统一处理文件的读取操作，最好提供自定义的异常类型
    protected void readData() throws Exception {
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        String error = "File doesnt exist at path " + getFilePath();

        // 推荐使用try-with-resources, 而不是try-finally
        try {
            if (isResourceInClassPath()) {
                InputStream inputStream = getClass().getResourceAsStream(getFilePath());
                if (inputStream == null) {
                    throw new Exception(error);
                }
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            } else {
                try {
                    fileReader = new FileReader(getFilePath());
                } catch (FileNotFoundException e) {
                    throw new Exception(error, e);
                }
                bufferedReader = new BufferedReader(fileReader);
            }
        } finally {
            if (fileReader != null) {
                try {
                    fileReader.close();
                } catch (IOException e) {
                    System.out.println("Error closing fileReader");
                }
            }
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    System.out.println("Error closing bufferedReader");
                }
            }
        }
    }

}
