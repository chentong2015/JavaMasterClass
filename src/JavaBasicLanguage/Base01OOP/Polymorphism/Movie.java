package JavaBasicLanguage.Base01OOP.Polymorphism;

// 多态的基本概念：
// Allows actions to act differently based on the actual action is being performed on
// Movie movie = randomMovie();
// movie.getName();
// movie.plot(); 调用到继承链上的最新版本，取决于变量引用的实例对象
public class Movie {

    private String name;

    public Movie(String name) {
        this.name = name;
    }

    public String plot() {
        return "No plot here";
    }

    public String getName() {
        return name;
    }
}
