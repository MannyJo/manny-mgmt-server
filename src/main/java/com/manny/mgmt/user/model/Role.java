package com.manny.mgmt.user.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String role;

    public Role() {
    }

    public Role(Long id, String role) {
        this.id = id;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public String getRole() {
        return role;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", role='" + role + '\'' +
                '}';
    }
}
