package com.manny.mgmt.user.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.manny.mgmt.storage.model.Storage;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.*;

@Entity
@Table(name = "`user`")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Email
    @Column(unique = true)
    private String username;
    private String password;
    @OneToOne
    @JoinColumn(name = "role_id", referencedColumnName = "id", columnDefinition = "bigint default 2")
    private Role role;
    @Column(name = "created_date")
    private Date createdDate = new Date();
    @ManyToMany(mappedBy = "users", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("users")
    private List<Storage> storage = new ArrayList<>();

    @Transient
    private boolean isAccountNonExpired;
    @Transient
    private boolean isAccountNonLocked;
    @Transient
    private boolean isCredentialsNonExpired;
    @Transient
    private boolean isEnabled;
    @Transient
    private Set<? extends GrantedAuthority> grantedAuthorities;
    @Transient
    private String newPassword;

    public User(Long id, @Email String username, String password, Role role, Date createdDate, List<Storage> storage, Set<? extends GrantedAuthority> grantedAuthorities, boolean isAccountNonExpired, boolean isAccountNonLocked, boolean isCredentialsNonExpired, boolean isEnabled, String newPassword) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
        this.createdDate = createdDate;
        this.storage = storage;
        this.grantedAuthorities = grantedAuthorities;
        this.isAccountNonExpired = isAccountNonExpired;
        this.isAccountNonLocked = isAccountNonLocked;
        this.isCredentialsNonExpired = isCredentialsNonExpired;
        this.isEnabled = isEnabled;
        this.newPassword = newPassword;
    }

    public User() {
    }

    public User(Long id,
                @Email String username,
                String password,
                Role role,
                Date createdDate,
                List<Storage> storages) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
        this.createdDate = createdDate;
        this.storage = storage;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public List<Storage> getStorage() {
        return storage;
    }

    public Role getRole() {
        return role;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorities;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        isAccountNonExpired = accountNonExpired;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        isAccountNonLocked = accountNonLocked;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        isCredentialsNonExpired = credentialsNonExpired;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    public void setGrantedAuthorities(Set<? extends GrantedAuthority> grantedAuthorities) {
        this.grantedAuthorities = grantedAuthorities;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", createdDate=" + createdDate +
                '}';
    }
}
