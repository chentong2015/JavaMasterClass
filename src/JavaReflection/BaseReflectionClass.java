package JavaReflection;

// Java Reflection 语言特性: Pascal, C, or C++没有这样的特性
// 支持一个Java Class获取和操作它内部所有成员
// It supports dynamic retrieval of information about classes and data structures by name
// Allows for their manipulation within an executing Java program
public class BaseReflectionClass {

    private String name;

    public BaseReflectionClass() {
        
    }

    public BaseReflectionClass(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
