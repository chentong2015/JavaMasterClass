package Object;

import java.util.Objects;

public class ObjectEquals {

    // TODO. 调用自定义类型的equals()方法
    // - equals()默认比较的是对象的reference引用是否相同
    // - equals()必须重定义来实现对象内部属性值的比较
    public static void main(String[] args) {
        User user1 = new User(1, "name 1");
        User user2 = new User(1, "name 1");

        if (Objects.equals(user1, user2)) {
            System.out.println("user1 == user2");
        }
        if (user1.equals(user2)) {
            System.out.println("user1 == user2 Equals");
        }
    }

    static class User {

        private int age;
        private String name;

        public User(int age, String name) {
            this.age = age;
            this.name = name;
        }

        // 自定义重写的equals()来进行比较属性值
        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            User user = (User) obj;
            return age == user.age && name.equals(user.name);
        }

        // 重写equals()方法的同时需要重写hashCode()
        @Override
        public int hashCode() {
            return super.hashCode();
        }
    }
}