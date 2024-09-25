package garbage_collection;

import java.util.ArrayList;
import java.util.List;

// TODO. GC能否回收"包含无法被GC的属性"的对象 ?
//  => 不能，因为属性中包含对象的引用(该引用无法被回收)
//  => 外层对象不会到Heap中重复新增object对象的存储空间，不占过多的内存
// 线程A创建一个Handler对象，其中一个属性被赋值为所有线程共享的object对象
// 线程B随后将其修改，将Handler对象的变量引用一个新创建的对象，做相同的属性赋值
// 则旧的Handler对象(包含一个无法被GC的属性)是否会被垃圾回收 ?
public class GCCollectObjects {

    private DemoField demoField = new DemoField();
    private DemoClass demoClass;

    public void testGcCollectObject() {
        for (int i=0; i<500; i++) {
            int finalI = i;
            new Thread(() -> {
                demoClass = new DemoClass(demoField, "name" + finalI);
                try {
                    Thread.sleep(30000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                demoClass.printField();
            }).start();
        }
    }

    public static void main(String[] args) {
        GCCollectObjects collectObjects = new GCCollectObjects();
        collectObjects.testGcCollectObject();
    }

    class DemoClass {
       private DemoField demoField;
       private String name;

       public DemoClass(DemoField demoField, String name) {
           this.demoField = demoField;
           this.name = name;
       }

       public void printField() {
           System.out.println(demoField);
       }
    }

    class DemoField {
        private List<String> list;

        public DemoField() {
            list = new ArrayList<>();
            for (int i=1; i < 1000; i++) {
                list.add("Item:" + i);
            }
        }
    }
}
