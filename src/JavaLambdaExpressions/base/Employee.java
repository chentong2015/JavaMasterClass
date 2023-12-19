package JavaLambdaExpressions.base;

public class Employee {

    private String name;
    private int age;
    private String gender;

    public Employee(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    @Override
    public String toString() {
        return getName() + getAge();
    }
}
