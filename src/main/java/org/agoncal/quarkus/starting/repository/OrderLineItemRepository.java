package org.agoncal.quarkus.starting.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

import org.agoncal.quarkus.starting.entity.OrderLineItem;

public class OrderLineItemRepository implements PanacheRepository<OrderLineItem> {
}
