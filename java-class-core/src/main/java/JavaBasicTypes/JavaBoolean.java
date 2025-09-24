package JavaBasicTypes;

// TODO. 注意两种类型的默认值
public class JavaBoolean {

    public static void main(String[] args) {
        FormBased formBased = new FormBased();
        System.out.println(formBased.isBool); // false
        System.out.println(formBased.getBoolean()); // null

        // TODO. Boolean类型的null默认值不能直接设置给bool类型的值
        formBased.setBool(formBased.getBoolean());
    }

    static class FormBased {
        private boolean isBool;  // 默认值为false
        private Boolean isBoolean; // 默认值为null

        public void setBool(boolean bool) {
            isBool = bool;
        }

        public Boolean getBoolean() {
            return isBoolean;
        }

        public void setBoolean(Boolean aBoolean) {
            isBoolean = aBoolean;
        }
    }

}
