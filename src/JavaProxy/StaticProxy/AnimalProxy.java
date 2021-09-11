package JavaProxy.StaticProxy;

// 该类型可以代理任何实现了Animal接口的类型，隔离了对实现类型的直接调用
public class AnimalProxy implements Animal {

    private Animal animal;

    public AnimalProxy(Animal animal) {
        this.animal = animal;
    }

    @Override
    public void eatFood(String foodName) {
        animal.eatFood(foodName);
    }

    @Override
    public boolean running() {
        return animal.running();
    }
}
