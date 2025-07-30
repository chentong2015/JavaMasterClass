package JavaFeatureOopClass.Encapsulation;

// 正确的封装模式
public class GoodEncapsulation {

    private String name;
    private int health;
    private String weapon;

    public GoodEncapsulation(String name, int health, String weapon) {
        this.name = name;
        // Add data validation
        if (health > 0 && health <= 100) {
            this.health = health;
        }
        this.weapon = weapon;
    }

    public void loadHealth(int damage) {
        this.health -= damage;
        if (this.health <= 0) {
            System.out.println("The player knocked out");
        }
    }

    public int getHealth() {
        return health;
    }
}
