package OtherTech.Lombok;

/**
 * Project Lombok:                    ===> 特别使用在JPA Entities
 * 1. 使用Lombok可以减少写基本方法: getters, setters, equals(), hashCode(), toString()
 * 2. Lombok将会在编译时自动的生成基本方法 ===> 通过 View > Structure 查看实际生成的成员和方法 !!!
 */
// @Getter // For all fields in the class
// @Setter
// @Slf4j
public class BaseLombok {

    // 1. settings > Plugins > Lombok 添加第三方插件
    // 2. settings > Build, Execution, Deployment > Compiler > Annotation Processors > Enable annotation processing + Obtain processors...
    // 3. Add Lombok dependency
    //    <dependency>
    //        <groupId>org.projectlombok</groupId>
    //        <artifactId>lombok</artifactId>
    //        <!-- 可以指定的从本地的路径添加依赖: 根据相对的路径找到local jar file, 注明和jar同样的版本version
    //        <version>edge</version>
    //        <scope>system</scope>
    //        <systemPath>${basedir}/lib/lombok-edge.jar</systemPath> -->
    //    </dependency>

    /**
     * @Getter: 注解filed之后，会编译自动生成标准的getter方法, getFieldName(), isFieldName() ...
     * @Setter: 注解filed之后，会编译自动生成标准的setter方法, setFieldName()
     * @ToString: 生成标准toString()方法
     * @EqualsAndHashCode: 自动生成equals(), hashCode()方法 ==> 重写类型的equals()方法时必须同时重写hashCode()方法
     * @RequiredArgsConstructor: 生成带指定参数的构造器
     * @slf4j: 生成private static field for SLF4J Logger log = LoggerFactory.getLogger(ClassName);
     */
    // @Getter
    // @Setter(AccessLevel.NONE) // No access to setter method
    private String name;
}
