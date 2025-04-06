package demo.mapper;

import demo.constant.UserPermission;
import demo.constant.UserRole;
import demo.entity.Permission;
import demo.entity.Role;
import demo.entity.User;
import demo.repository.PermissionRepository;
import demo.repository.RoleRepository;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.panache.common.Parameters;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static demo.constant.UserRole.USER;
import static io.quarkus.panache.common.Parameters.with;
import static java.util.stream.Collectors.toSet;

@ApplicationScoped
public class UserMapperImpl {

    @Inject
    RoleRepository roleRepository;
    @Inject
    PermissionRepository permissionRepository;

    public User mapToEntity(String userName, Map<String, String> userAttributes) {
        User user = new User();
        user.setName(userAttributes.get("name"));
        user.setEmail(userAttributes.get("email"));
        user.setFullName(userAttributes.get("name"));
        user.setIdpUserName(userName);
        user.setIdpSub(userAttributes.get("sub"));
        user.addRoles(buildUserRoles());
        user.addPermissions(buildUserPermissions());
        return user;
    }

    private Set<Permission> buildUserPermissions() {
        List<String> permissionNames = UserPermission.getPermissions().stream().toList();
        List<Permission> permissions = permissionRepository
                .find("name IN :names", with("names", permissionNames))
                .list();
        return new HashSet<>(permissions);

//    return UserPermission.getPermissions()
//            .stream()
//            .map(name -> em.getReference(Permission.class, name))
//            .collect(Collectors.toSet());

//        List<String> permissionNames = UserPermission.getPermissions().stream().toList();
//        List<Permission> permissions = Permission
//                .find("name IN :names", Parameters.with("names", permissionNames)).list();
//        return new HashSet<>(permissions);

    }

    private Set<Role> buildUserRoles() {
//        Role managedRole = em.getReference(Role.class, UserRole.USER.name());
//        Role role = Role.findById(UserRole.USER.name());
        Role role = roleRepository
                .find("name=:name", with("name", USER.name()))
                .singleResult();
        return Set.of(role);
    }

}
