package org.agoncal.quarkus.starting.dto;


import org.agoncal.quarkus.starting.entity.Customer;
import org.agoncal.quarkus.starting.entity.Order;

import java.time.LocalDate;
import java.util.List;

public class OrderDTO {
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private LocalDate date;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Order.CHANNEL getChannel() {
        return channel;
    }

    public void setChannel(Order.CHANNEL channel) {
        this.channel = channel;
    }

    public List<OrderLineItemDTO> getLines() {
        return lines;
    }

    public void setLines(List<OrderLineItemDTO> lines) {
        this.lines = lines;
    }

    private Customer customer;
    private Order.CHANNEL channel;
    private List<OrderLineItemDTO> lines;

}
