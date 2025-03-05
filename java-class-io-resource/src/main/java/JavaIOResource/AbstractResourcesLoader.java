package JavaIOResource;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Stream;

// TODO. 设计抽象ResourceLoader API, 根据资源不同位置进行加载
public abstract class AbstractResourcesLoader {

    // 由继承类来具体实现加载的资源信息
    protected abstract boolean isResourceInClasspath();

    protected abstract String getFilepath();

    protected Stream<String> readFileData() throws Exception {
        if (isResourceInClasspath()) {
            InputStream in = this.getClass().getResourceAsStream(getFilepath());
            assert in != null;
            try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in))) {
                return bufferedReader.lines();
            }
        }

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(getFilepath()))) {
            return bufferedReader.lines();
        }
    }
}
