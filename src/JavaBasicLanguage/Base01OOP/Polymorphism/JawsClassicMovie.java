package JavaBasicLanguage.Base01OOP.Polymorphism;

public class JawsClassicMovie extends Movie {

    public JawsClassicMovie() {
        super("Jaws");
    }

    @Override // 不写注解也表示是重写 !!!
    public String plot() {
        return getClass().getSimpleName() + " plot ";
    }

}
