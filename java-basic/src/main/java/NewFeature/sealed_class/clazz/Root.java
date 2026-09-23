package NewFeature.sealed_class.clazz;

// sealed 密封类型必须有继承类型
public sealed class Root permits SubRootSealed, SubRoot {
}
