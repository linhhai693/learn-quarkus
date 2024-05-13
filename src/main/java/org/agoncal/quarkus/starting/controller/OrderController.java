package org.agoncal.quarkus.starting.controller;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import jakarta.annotation.security.RolesAllowed;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.agoncal.quarkus.starting.dto.OrderDTO;
import org.agoncal.quarkus.starting.entity.Book;
import org.agoncal.quarkus.starting.entity.Order;
import org.agoncal.quarkus.starting.entity.user.Permission;
import org.agoncal.quarkus.starting.repository.OrderRepository;

import java.util.List;
import java.util.stream.Collectors;

@Path("/api/orders")
@RolesAllowed(Permission.Resource.ORDER)
@Produces(MediaType.APPLICATION_JSON)
public class OrderController {

    OrderRepository orderRepository = new OrderRepository();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response createOrder(OrderDTO orderDTO) {

        orderRepository.save(orderDTO);
        return Response.status(Response.Status.CREATED).build();
    }


    @GET
    public List<OrderDTO> getOrders() {
        OrderRepository orderRepository = new OrderRepository();
        PanacheQuery<Order> query = orderRepository.findAll();
        List<Order> orders = query.list();
        return orders.stream().map(Order::builderDTO).collect(Collectors.toList());
    }


    @GET
    @Path("id")
    public OrderDTO getOrder(@QueryParam("id") Long id) {
        return null;
    }

}
