package JavaIO.IOPackage;

import JavaIO.DataModel.MyClassA;
import JavaIO.DataModel.MyClassB;

import java.io.IOException;

public class JavaIoCloseable {

    // TODO. 使用try-with-with-Resources时，会自动调用类型close()方法(自定义资源的释放等操作)
    //  会先释放MyClassB, 然后释放MyClassA
    public void testCloseWithTry() throws IOException {
        try (MyClassA myClassA = new MyClassA();
             MyClassB myClassB = new MyClassB(myClassA)) {
            myClassB.print();
        }
    }

    // TODO. 非try-with-with-Resources形式，释放资源时需要判断对象是否为null
    //  如果为null，则抛出异常java.lang.NullPointerException
    public void testCloseWithoutTry() throws IOException {
        MyClassA myClassA = new MyClassA();
        MyClassB myClassB = new MyClassB(myClassA);

        // to do something
        if (myClassB != null) {
            myClassB.close();
        }
        if (myClassA != null) {
            myClassA.close();
        }
    }
}