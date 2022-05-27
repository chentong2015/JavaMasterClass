package JavaIO.resources;

import java.io.InputStream;

public class BaseResources {

    // For maven project + apache.commons.io
    // TODO. 这里资源文件的获取路径，需要和this.getClass()当前类的路径一致(package)
    public void testGetResources() {
        InputStream resourceAsStream = this.getClass().getResourceAsStream("test.json");
        // byte[] content = toByteArray(resourceAsStream);
    }

    // For Spring framework project
    // 指定加载/Resources下的文件
    public void getFileInputStreamFromResources() {
        // Resource resource = new ClassPathResource("products1.json");
        // FileInputStream file = new FileInputStream(resource.getFile());
        // byte[] content = ByteStreams.toByteArray(file);
    }
}
