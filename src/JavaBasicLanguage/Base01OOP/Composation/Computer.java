package JavaBasicLanguage.Base01OOP.Composation;

public class Computer {

    private String model;

    // Composition 组合(一种关系模型)    ===> 强耦合的关系， 需要解耦 !!!!
    // Computer Has-A monitor, monitor is part (component) of computer
    private Monitor monitor;

    public Computer(String model, Monitor monitor) {
        this.model = model;
        this.monitor = monitor;
    }

    // 直接使用Composition包含的类型来实现逻辑
    public void powerUp() {
        monitor.loadMonitor();
        System.out.println("Power up");
    }

}
