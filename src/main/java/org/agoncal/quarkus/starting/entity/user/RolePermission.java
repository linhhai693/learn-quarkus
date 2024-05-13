package org.agoncal.quarkus.starting.entity.user;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "role_permission")
public class RolePermission extends PanacheEntity {


    @ManyToOne
    @JoinColumn(name = "role_id")
    public Role role;

    @ManyToOne
    @JoinColumn(name = "permission_id")
    public Permission permission;


}
