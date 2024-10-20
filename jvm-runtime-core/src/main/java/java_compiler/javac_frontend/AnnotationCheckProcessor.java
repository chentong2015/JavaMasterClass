package java_compiler.javac_frontend;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import java.util.Set;

// 插入式注解处理器 "*"表示支持所有Annotations
@SupportedAnnotationTypes("*")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class AnnotationCheckProcessor extends AbstractProcessor {

    private NameChecker nameChecker;

    // 初始化注解处理器，提供处理器框架的上下文
    @Override
    public void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        nameChecker = new NameChecker(processingEnv);
    }

    /**
     * Java编译器在执行注解处理器时要调用的方法，添加自定的代码验校功能
     * 对输入的语法树的各个节点进行名称检查
     *
     * @param annotations 注解处理器所处理的注解集合
     * @param roundEnv    访问轮次中抽象语法树的结点，每个树的结点都表示成Element
     * @return
     */
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        if (!roundEnv.processingOver()) {
            for (Element element : roundEnv.getRootElements())
                nameChecker.checkNames(element);
        }
        return false;
    }
}
