package com.danylko.yourburger.enums;

public enum Permission {
    CUSTOMERS_READ("customers:read"),
    CUSTOMERS_WRITE("customers:write"),
    ADMIN_READ("admin:read"),
    ADMIN_WRITE("admin:write")
    ;

    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
