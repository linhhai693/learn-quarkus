package org.agoncal.quarkus.starting.dto;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.agoncal.quarkus.starting.entity.Customer;

import java.time.LocalDate;


public class CustomerDTO {
    private Long id;
    private String name;
    private LocalDate birth;
    private   Customer.Status status;

    public Customer.Status getStatus() {
        return status;
    }

    public void setStatus(Customer.Status status) {
        this.status = status;
    }

    public LocalDate getBirth() {
        return birth;
    }

    public void setBirth(LocalDate birth) {
        this.birth = birth;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
