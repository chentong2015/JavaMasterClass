package JavaBasicLanguage.Base02PackagesScope;

// Package: namespaces 避免名称的冲突
// 1. 集合一组相关(功能)的类型或者是接口
// 2. 同一个包中的类型之间是可以直接访问的，但是对外包存在访问约束

// Package命名规则
// 1. 全部是小写
// 2. 有关键字的时，需要使用下划线
// 3. 可以包含.但是不能含有其他的字符, 每一个.下面会划分成一个新的目录
// java.lang 包含基本类型, 自动被引入
// java.io
// org.xml.sax.helpers
// Switch.supplier.com  -> com.supplier._switch
// 1World.com  -> com._1world
// Export-exchange.com  -> com.experts._exchange

// 1. 通过artifact打包项目类型到一个.jar包中, artifact > build ===> 删除Main class, 针对整个项目的打包 !!!
// 2. 通过Project Structure > Libraries > 引入打包的java source code (指定import的路径)

import org.w3c.dom.Node;

import java.awt.*;

import static JavaBasicLanguage.Base02PackagesScope.example.mylibrary.Series.factor;
// 在不使用类型的情况下，直接使用类型的静态方法 > import static

// import org.w3c.dom.Node; 不能再引入同名的类型 !
// 如果这里不写public，则只能在当前的包中可见(package-private)，外包不能看到该类型
public class BasePackage extends Frame {

    // 1. 只在定义类型的内部可见
    private String privateStr;

    // 2. 默认不写 package-private: 对当前包的的所有Classes可见 

    // 3.1 对当前的包是全部可见的 (实例对象 + 继承的子类)

    // 3.2 对外部的包只有继承的子类中可见 !!!
    protected String protectedStr;

    // 4. 在任何地方，包内或是包外都能被可见
    public String publicStr;

    public static void main(String[] args) {
        // 一个使用import; 同时避开直接注明 !!
        Node node = null;

        // 可以使用import来引入, 或者是直接注明, 没有使用都需要完整的注明 !!
        org.w3c.dom.Node anotherNode = null;

        // Series series = new Series(); 使用声明的类型，需要import Series
        // Series.factor(100);
        long result = factor(100); // 不通过类型直接使用类型的静态方法 import static Series.factor;
    }
}
