package Database.Privileges;

/**
 *
 * @author lord_
 */
public enum UserPrivileges {
    GUEST(9999, "guest"), // nO pRIVILEGES
    STUDENT(5, "student"),
    TEACHER(4, "teacher"),
    ADMINISTRATOR(2, "administrator"),
    ADMIN(1, "admin");
    
    private int privValue;
    private String name;

    private UserPrivileges(int privValue, String name) {
        this.privValue = privValue;
        this.name = name;
    }

    public int getPrivValue() {
        return privValue;
    }
    public String getName() {
        return name;
    }
}
