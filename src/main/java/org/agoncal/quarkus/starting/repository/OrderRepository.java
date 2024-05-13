package org.agoncal.quarkus.starting.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import org.agoncal.quarkus.starting.dto.OrderDTO;
import org.agoncal.quarkus.starting.dto.OrderLineItemDTO;
import org.agoncal.quarkus.starting.entity.Order;
import org.agoncal.quarkus.starting.entity.OrderLineItem;

public class OrderRepository implements PanacheRepository<Order> {

    OrderLineItemRepository orderLineItemRepository = new OrderLineItemRepository();

    public void save(OrderDTO order) {
        Order orderEntity = new Order(order);
        persist(orderEntity);
        order.setId(orderEntity.getId());
        if(order.getLines() == null || order.getLines().isEmpty()) {
            return;
        }
        for(OrderLineItemDTO orderLineItemDTO : order.getLines()) {
            OrderLineItem orderLineItem = new OrderLineItem(orderEntity.getId(), orderLineItemDTO);
            orderLineItemRepository.persist(orderLineItem);
        }
    }

    public Order findById(String id) {
        return find("id", id).firstResult();
    }
}
