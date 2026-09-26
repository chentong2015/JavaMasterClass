package core_types;

public class JavaBoolean {

    // TODO. Boolean类型的null默认值不能直接设置给bool类型的值
    public static void main(String[] args) {
        FormBased formBased = new FormBased();
        System.out.println(formBased.isBool); // false
        System.out.println(formBased.getBoolean()); // null

        // NullPointerException Error
        formBased.setBool(formBased.getBoolean());
    }

    static class FormBased {
        private boolean isBool;    // 默认值为false
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
