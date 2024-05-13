package org.agoncal.quarkus.starting.entity.user;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.List;

//saler/reporter/admin/storer
@Entity
public class Role extends PanacheEntity {

    @Column(unique = true, nullable = false)
    public String name;

    public String description;

    @OneToMany(mappedBy = "role")
    public List<RoleUser> roleUsers;

    @OneToMany(mappedBy = "role")
    public List<RolePermission> rolePermissions;



    public Role(String nameValue) {
        name = nameValue;
    }

    public Role() {

    }
}
