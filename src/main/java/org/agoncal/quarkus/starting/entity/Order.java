package org.agoncal.quarkus.starting.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import org.agoncal.quarkus.starting.dto.OrderDTO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "order_tbl")
public class Order{

    public Order(Long orderId) {
    }

    public Order() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue
    private Long id;

    public enum CHANNEL {
        Online,Offline
    }

    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Column(nullable = false)
    private CHANNEL channel;

    @OneToMany(mappedBy = "order")
    private List<OrderLineItem> lines;

    public Order(OrderDTO orderDTO){
        this.channel = orderDTO.getChannel();
        this.date = LocalDate.now();
        this.customer  = orderDTO.getCustomer();
        this.lines = new ArrayList<>();
    }

    public OrderDTO builderDTO(){
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setCustomer(customer);
        orderDTO.setChannel(channel);
        orderDTO.setDate(date);
        orderDTO.setLines(lines.stream().map(OrderLineItem::buildOrderLineItemDTO).collect(Collectors.toList()));
        return  orderDTO;
    }
}
