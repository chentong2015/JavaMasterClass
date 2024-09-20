package concurrent_apis.list;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

// TODO. 不同类型的集合均有对应的线程安全的集合
public class JavaConcurrentList {

    // 创建线程安全的List
    private final List<String> listSafe1 = Collections.synchronizedList(new ArrayList<>());
    private final List<String> listSafe2 = Collections.synchronizedList(new LinkedList<>());
}
