package org.agoncal.quarkus.starting.entity.user;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;

import java.util.Objects;

@Entity
public class Permission extends PanacheEntity {

    public Permission() {

    }

    public static class Resource{
        public static  final String BOOK = "book";
        public static  final String ORDER = "order";
        public static final String ADMIN = "admin";
        public static  final String PURCHASE = "purchase";
        public static  final String REPORT = "report";
    }

    public static class Action{
        public static  final String READ = "read";
        public static  final String CREATE = "create";
        public static  final String DELETE = "delete";
        public static  final String UPDATE = "manage";
    }

    public String resource;
    //@RolesValue
    public String action;

    public Permission(String resource1, String action1) {
        resource = resource1;
        action = action1;
    }

    public int hashCode() {
        return this.action.hashCode() + this.resource.hashCode();
    }

    public boolean equals(Permission obj) {
        return Objects.equals(obj.action, this.action) && Objects.equals(obj.resource, this.resource);
    }

    public String toString() {
        return this.action + " " + this.resource;
    }
}
