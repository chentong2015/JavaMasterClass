package JavaBasicOOP.BaseEnum.project;

public class DBTypeEnumTest {

    public static void main(String[] args) {
        // Returns the enum constant of this type with the specified name.
        // The string must match exactly an identifier used to declare an enum constant in this type.
        // (Extraneous whitespace characters are not permitted.)
        DBType dbType = DBType.valueOf("name");

        DBType dbType1 = DBType.MS_SQL_SERVER;
    }
}
