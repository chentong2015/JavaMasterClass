package Object;

import java.util.Objects;

public class ObjectEquals {

    public static void main(String[] args) {
        User user1 = new User(1, "name 1");
        User user2 = new User(1, "name 1");

        // TODO. 默认Objects.equals()仅比较对象的引用
        // 调用自定义的重写的equals()来进行比较
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
    }
}
