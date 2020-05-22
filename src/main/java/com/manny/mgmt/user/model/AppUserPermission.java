package com.manny.mgmt.user.model;

public enum AppUserPermission {
    READ("read"),
    WRITE("write");

    private final String permission;

    AppUserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
