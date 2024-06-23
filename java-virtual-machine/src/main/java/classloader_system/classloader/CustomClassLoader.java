package classloader_system.classloader;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

// TODO: 为什么要自定义类加载器 ?
// 在某些特殊的场景下，需要加载网络上的资源文件，或者磁盘上指定文件路径下的文件
// 这些class类文件无法通过父类的加载器来实现加载
public class CustomClassLoader extends ClassLoader {

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        try {
            // 从提供的路径中解析并获取出class文件的二级制流
            String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";
            InputStream is = getClass().getResourceAsStream(fileName);
            if (is == null) return super.loadClass(name);
            byte[] b = new byte[is.available()];
            is.read(b);
            return defineClass(name, b, 0, b.length);
        } catch (IOException e) {
            throw new ClassNotFoundException(name);
        }
    }

    // 自定义在什么位置路径下，通过名称找到class文件
    // 然后根据从class文件中读取的数据，加载到内存并生成Class<?>
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try {
            byte[] data = getByteByClasspathName(name);
            return defineClass(name, data, 0, data.length);
        } catch (IOException exception) {
            throw new ClassNotFoundException();
        }
    }

    // 提供的Classpath名称，返回找到的class文件流(字节数组)
    // com.ctong.main.MyClassLoader --> com/ctong/main/MyClassLoader.class
    private byte[] getByteByClasspathName(String name) throws IOException {
        String classPath = "...";
        String formattedName = name.replaceAll("\\.", "/");
        String classFilePath = classPath + "/" + formattedName + ".class";
        // 将IO Stream流的信息读到字节数组中
        FileInputStream inputStream = new FileInputStream(classFilePath);
        int size = inputStream.available();
        byte[] data = new byte[size];
        inputStream.read(data);
        inputStream.close();
        return data;
    }
}


