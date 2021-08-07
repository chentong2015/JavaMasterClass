package JavaBasicLanguage.Base01OOP.OOP.Polymorphism;

// 多态的基本概念：Allows actions to act differently based on the actual action is being performed on
// Movie movie = randomMovie();
// movie.getName();
// movie.plot(); 多态 调用到继承链上的最新版本 决定于movie引用的实例对象 !!!!
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
