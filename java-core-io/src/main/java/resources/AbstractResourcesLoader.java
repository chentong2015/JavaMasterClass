package resources;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Stream;

// TODO. 设计抽象ResourceLoader兼容加载不同资源路径
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
