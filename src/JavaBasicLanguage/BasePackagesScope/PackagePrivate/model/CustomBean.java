package JavaBasicLanguage.BasePackagesScope.PackagePrivate.model;

public class CustomBean {

    // package-private包私有属性只能在当前包被访问
    // - 当前包中的对象调用属性
    // - 在当前包中的所有继承类中访问
    String state;

}
