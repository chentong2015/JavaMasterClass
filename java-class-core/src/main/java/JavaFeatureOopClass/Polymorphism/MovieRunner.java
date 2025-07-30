package JavaFeatureOopClass.Polymorphism;

public class MovieRunner extends Movie {

    public MovieRunner() {
        super("Runner ");
    }

    @Override
    public String plot() {
        return getClass().getSimpleName() + " plot";
    }
}
