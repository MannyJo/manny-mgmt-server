package com.manny.mgmt.user.model;

import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.manny.mgmt.user.model.AppUserPermission.*;

public enum AppUserRole {
    USER(Sets.newHashSet(READ, WRITE)),
    ADMIN(Sets.newHashSet(READ, WRITE));

    private final Set<AppUserPermission> permissions;

    AppUserRole(Set<AppUserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<AppUserPermission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
        Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
                .map(m -> new SimpleGrantedAuthority(m.getPermission()))
                .collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return permissions;
    }

    public Long getId() {
        switch (name()) {
            case "ADMIN":
                return (long) 1;
            default:
                return (long) 2;
        }
    }
}
