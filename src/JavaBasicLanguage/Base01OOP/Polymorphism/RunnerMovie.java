package JavaBasicLanguage.Base01OOP.Polymorphism;

public class RunnerMovie extends Movie {

    public RunnerMovie() {
        super("Runner ");
    }

    @Override
    public String plot() {
        return getClass().getSimpleName() + " plot";
    }
}
