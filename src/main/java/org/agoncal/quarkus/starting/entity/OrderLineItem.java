package org.agoncal.quarkus.starting.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import org.agoncal.quarkus.starting.dto.OrderLineItemDTO;
import org.agoncal.quarkus.starting.repository.OrderRepository;

@Entity
@Table(name = "order_line_item")
public class OrderLineItem {

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @Column(nullable = false)
    private int quantity;
    @Id
    @GeneratedValue
    private Long id;

    public OrderLineItem(Long orderID, OrderLineItemDTO orderLineItemDTO) {
        OrderRepository orderRepository = new OrderRepository();
        this.order = orderRepository.findById(orderID);
        this.book = Book.findById(orderLineItemDTO.getBookId());
        this.quantity = orderLineItemDTO.getGetQuantity();
    }

    public OrderLineItem() {

    }

    public OrderLineItemDTO buildOrderLineItemDTO() {
        OrderLineItemDTO orderLineItemDTO = new OrderLineItemDTO();
        orderLineItemDTO.setId(this.id);
        orderLineItemDTO.setGetQuantity(this.quantity);
        orderLineItemDTO.setBook(this.book);
        return orderLineItemDTO;
    }
}
