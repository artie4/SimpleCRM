package app.util;

public enum Roles {

    ROLE_ADMIN("ROLE_ADMIN"),
    ROLE_EMPLOYEE("ROLE_EMPLOYEE"),
    ROLE_MANAGER("ROLE_MANAGER");


    private Roles(String roleName) {
        this.roleName = roleName;
    }

    String roleName;


}
