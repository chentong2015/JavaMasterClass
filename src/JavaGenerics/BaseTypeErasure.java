package JavaGenerics;

/**
 * 为了实现泛型，Java编译器将"类型擦除"应用于：
 * 1. 在编译的时候做类型的check & replace, 确定具体的类型, 反则使用Object, 最后生成的bytecode是常规的类，接口和方法
 * 2. 添加类型的转换，以确保类型的安全
 * 3. 生成bridge methods以便在泛型中保留多态
 */
public class BaseTypeErasure {
    
    // 类型擦除可确保不会为参数化类型创建新的类, 因此泛型不会产生运行时开销
}
