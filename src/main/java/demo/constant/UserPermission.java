package demo.constant;

import java.util.Set;

public enum UserPermission {
    USER_READ("USER:READ"),
    USER_WRITE("USER:WRITE");

    private final String name;

    UserPermission(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Set<String> getPermissions() {
        return Set.of(USER_READ.getName(), USER_WRITE.getName());
    }
}
