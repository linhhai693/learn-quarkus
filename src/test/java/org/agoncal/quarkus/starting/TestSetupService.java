package org.agoncal.quarkus.starting;

import groovy.lang.Singleton;
import io.quarkus.elytron.security.common.BcryptUtil;
import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.event.Observes;
import jakarta.transaction.Transactional;
import org.agoncal.quarkus.starting.dto.BookDTO;
import org.agoncal.quarkus.starting.entity.Book;
import org.agoncal.quarkus.starting.entity.user.*;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Singleton
public class TestSetupService {

    @Transactional
    public void onStartup(@Observes StartupEvent ev) {
        BookDTO book = new BookDTO();
        book.setTitle("Book 1");
        book.setAuthor("John Doe");
        Book book2 = new Book();
        book2.builder(book);
        book2.persist();
    }


    @Transactional
    public void addUserPermission(@Observes StartupEvent evt) {
        User saler = new User();
        saler.username = "saler";
        saler.password = BcryptUtil.bcryptHash("saler");
        //saler.role = "saler";
        saler.persist();

        initPermissionForUser(saler);

        User admin = new User();
        admin.username = "admin";
        admin.password = BcryptUtil.bcryptHash("admin");
        //admin.role = "admin";
        admin.persist();

        initPermissionForUser(admin);
    }

    private static void initPermissionForUser(User user) {
        List<String> actions = Arrays.asList(Permission.Action.CREATE, Permission.Action.READ, Permission.Action.UPDATE, Permission.Action.DELETE);
        Role role = new Role(user.username);
        role.persist();
        RoleUser roleUser = new RoleUser();
        roleUser.role = role;
        roleUser.user = user;
        roleUser.persist();

        String resource = null;
        if(Objects.equals(user.username, "saler")){
            resource = Permission.Resource.ORDER;
        }else if(Objects.equals(user.username, "admin")){
            resource = Permission.Resource.BOOK;
        }

        for(String action : actions) {
            Permission permission = new Permission(resource, action);
            permission.persist();
            RolePermission rolePermission = new RolePermission();
            rolePermission.role = role;
            rolePermission.permission = permission;
            rolePermission.persist();
        }
    }
}
