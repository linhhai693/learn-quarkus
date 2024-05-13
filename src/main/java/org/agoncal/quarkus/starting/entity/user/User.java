package org.agoncal.quarkus.starting.entity.user;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.security.jpa.*;
import jakarta.inject.Inject;
import jakarta.persistence.*;
import org.hibernate.service.spi.InjectService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "user_tbl")
@UserDefinition
public class User extends PanacheEntity {
    @Username
    public String username;


    @Password()
    public String password;

    @OneToMany(mappedBy = "user")
    public List<RoleUser> roleUsers;

    @Roles
    public Set<String> getPermissionValues (){
        Set<String> permissionValues = new HashSet<>();
        Set<Role> roles = new HashSet<Role>();

        if(roleUsers == null){
            return null;
        }
        for(RoleUser roleUser : roleUsers){
            roles.add(roleUser.role);
        }

        Set<RolePermission> rolePermissions = new HashSet<>();
        for(Role role : roles){
            rolePermissions.addAll(role.rolePermissions);
        }

        Set<Permission> permissions = new HashSet<>();
        for(RolePermission rolePermission : rolePermissions){
            permissions.add(rolePermission.permission);
        }

        for(Permission permission : permissions){
            permissionValues.add(permission.resource);
        }
        return permissionValues;
    }


    public User(){

    }


}
