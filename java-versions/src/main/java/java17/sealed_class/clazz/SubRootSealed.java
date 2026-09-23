package java17.sealed_class.clazz;

// 标记继承类型为密封类型，继续开放继承关系
public sealed class SubRootSealed extends Root permits SubRootSealedPlus {
}
