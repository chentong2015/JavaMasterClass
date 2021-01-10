package JavaBasicLanguage.Base05OOP.Polymorphism;

public class RunnerMovie extends Movie {

    public RunnerMovie() {
        super("Runner ");
    }

    @Override
    public String plot() {
        return getClass().getSimpleName() + " plot";
    }
}
