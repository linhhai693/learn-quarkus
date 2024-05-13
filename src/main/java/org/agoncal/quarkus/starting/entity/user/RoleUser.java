package org.agoncal.quarkus.starting.entity.user;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "role_user")

public class RoleUser extends PanacheEntity {

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    public Role role;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    public User user;



}
