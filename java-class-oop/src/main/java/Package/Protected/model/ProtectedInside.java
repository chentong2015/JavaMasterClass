package Package.Protected.model;

public class ProtectedInside {

    // 同package包中对象可以获取protected的属性
    public static void main(String[] args) {
        MyClassA myClassA = new MyClassB();
        myClassA.name = "new name";
        myClassA.title = "new title";
    }
}
