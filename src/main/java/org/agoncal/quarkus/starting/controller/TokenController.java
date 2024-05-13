package org.agoncal.quarkus.starting.controller;

import io.quarkus.elytron.security.common.BcryptUtil;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.quarkus.security.Authenticated;
import io.smallrye.jwt.build.Jwt;
import io.smallrye.jwt.build.JwtClaimsBuilder;
import jakarta.annotation.security.RolesAllowed;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.agoncal.quarkus.starting.dto.UserDTO;
import org.agoncal.quarkus.starting.entity.user.*;
import org.eclipse.microprofile.jwt.Claims;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

@Path("/mytokens")
@Authenticated
public class TokenController {

    @GET
    @RolesAllowed("admin")
    @Produces(MediaType.TEXT_PLAIN)
    public String encodeToken() {
        return "this is a test";
    }

    /**
     * Returns the tokens available to the application.
     * This endpoint exists only for demonstration purposes.
     * Do not expose these tokens in a real application.
     *
     * @return an HTML page containing the tokens available to the application.
     */

    @POST
    @Transactional
    public Response createToken(UserDTO userDTO ) {

        List<PanacheEntityBase> users = User.findAll().list();
        if(users == null || users.isEmpty()){
            initDemoData();
        }

        // Dummy authentication logic, replace with your actual authentication logic
        User user = getUser(userDTO.getUserName(), userDTO.getPassword());
        if (user == null) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }

        // Generate JWT token
        String jwtToken = generateJWT(user);

        // Return JWT token in the response
        return Response.ok().entity(jwtToken).build();
    }

    private void initDemoData() {
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


    private User getUser(String username, String password) {
        User user = User.find("username=?1", username).firstResult();
        if (user == null) {
            return null;
        }

        String passwordInDB = user.password;
        if(BcryptUtil.matches(password, passwordInDB)){
            return user;
        }
        return null;
    }

    private String generateJWT(User user) {
        // Prepare claims for the JWT token
        JwtClaimsBuilder claims = Jwt.claims();
        claims.issuer("quarkus-example");
        claims.subject(user.username);
        claims.claim(Claims.upn.name(), user.username);



        Calendar now = Calendar.getInstance();
        claims.issuedAt(now.getTimeInMillis());
        now.add(Calendar.MINUTE, 30);
        claims.expiresAt(now.getTimeInMillis());



        // Sign the JWT token
        return claims.jws().sign();
    }


}
