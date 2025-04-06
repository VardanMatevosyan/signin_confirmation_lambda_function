package demo.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static jakarta.persistence.FetchType.LAZY;
import static lombok.AccessLevel.PRIVATE;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "user", schema = "aws-demo")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "idp_sub", nullable = false, unique = true)
    private String idpSub;

    @Column(name = "email")
    private String email;

    @Column(name = "name")
    private String name;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "idp_username", nullable = false, unique = true)
    private String idpUserName;

    @ManyToMany(fetch = LAZY)
    @JoinTable(
            name = "user_role",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_name")})
    @Setter(value = PRIVATE)
    private Set<Role> roles = new HashSet<>();

    @ManyToMany(fetch = LAZY)
    @JoinTable(
            name = "user_permission",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "permission_name")})
    @Setter(value = PRIVATE)
    private Set<Permission> permissions = new HashSet<>();

    public void addRole(Role role) {
        this.roles.add(role);
    }

    public void addRoles(Set<Role> roles) {
        this.roles.addAll(roles);
    }

    public void addPermission(Permission permission) {
        this.permissions.add(permission);
    }

    public void addPermissions(Set<Permission> permissions) {
        this.permissions.addAll(permissions);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(id, user.id)
            && Objects.equals(idpSub, user.idpSub)
            && Objects.equals(email, user.email)
            && Objects.equals(name, user.name)
            && Objects.equals(fullName, user.fullName)
            && Objects.equals(idpUserName, user.idpUserName);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
