package HotelManagement.employee;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permissions {
    SUPERUSER_READ("admin:read"),
    SUPERUSER_CREATE("admin:create"),
    SUPERUSER_UPDATE("admin:update"),
    SUPERUSER_DELETE("admin:delete"),
    ADMIN_READ("admin:read"),
    ADMIN_CREATE("admin:create"),
    ADMIN_UPDATE("admin:update"),
    ADMIN_DELETE("admin:delete"),
    MANAGER_READ("management:read"),
    MANAGER_CREATE("management:create"),
    MANAGER_UPDATE("management:update"),
    MANAGER_DELETE("management:delete");


    public final String permission;
}
