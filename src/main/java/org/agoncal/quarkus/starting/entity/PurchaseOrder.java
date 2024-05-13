package org.agoncal.quarkus.starting.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import net.bytebuddy.build.ToStringPlugin;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name="purcharse_order")
public class PurchaseOrder extends PanacheEntity {

    private LocalDate date;

    @OneToMany(mappedBy = "purchaseOrder")
    private List<PurchaseOrderLineItem> purchaseOrderLineItems;
}
