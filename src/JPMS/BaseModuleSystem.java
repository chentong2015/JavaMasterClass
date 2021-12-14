package JPMS;

// JPMS: Java Platform Module System
// 1. Java SE 9 Platform 被划分成不同的modules set集
// 2. Java Application is collection of modules 应用可以看成是module的集合
// 3. Module看成是Application应用内的可重用的组件components, packages的上层
// 4. 可以创建不同的Unit module, 共同构成application, 而无需完整的编译大型项目

// Project Jigsaw 项目来源
// TODO: JMPS解决了一个怎样的问题? 模块系统的访问性和迁移
//  http://openjdk.java.net/projects/jigsaw/spec/sotms/
//  http://seanthefish.com/2018/03/29/module-system/index.html
// 1. Scalable platform: 平台的可缩放性的能力
//    scale platform down to smaller computing devices, monolithic runtime
// 2. Security: organization, internal API hidden 拥有更好的Module接口去提高平台的安全性
// 3. Performance: smaller with only necessary runtimes, faster
// 4. Developer Experience: with module platform combination 更容易构建项目和类库
public class BaseModuleSystem {

    // 1. 一个模块相当于是package包的容器
    //    A module is a named collection fo data and code, as container of packages
    //
    // 2. two types of modules
    //    2.1 named modules: Automatic Module | Normal Module (Basic Module | Open Module)
    //        > Normal Module:
    //          需要module-info.java 描述文件
    //          包含module的metadata元数据信息位于module的根目录, 默认不是export all packages
    //        > Open Module:
    //          makes all packages inside de module accessible for deep refection
    //          第三方类库Spring可通过反射在运行的时候拿到内部的类型
    //    2.2 unnamed modules: add JAR file to the module path,
    //        export all packages by default 外部添加的Jar看成是不具名的module
    //
    // 3. Aggregator Module: collect and export the contents of other modules
    //    当一些module依赖3个modules时，可以将这3个module聚合到一个aggregator module
    //
    // 4. Module path: used by the compiler to find and resolve modules
    // 5. Classpath: represents a sequence of JAR files
}
