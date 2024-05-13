package org.agoncal.quarkus.starting.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.runtime.annotations.RegisterForReflection;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.agoncal.quarkus.starting.dto.BookDTO;
import org.agoncal.quarkus.starting.dto.CustomerDTO;

import java.time.LocalDate;

@Entity
@Table(name = "persontbl")
@RegisterForReflection
public class Customer extends PanacheEntity {
    public enum Status {
        Alive,
        Deceased
    }

    @Column(nullable = false)
    private String name;
    private LocalDate birth;
    private   Status status;


    public CustomerDTO builderDTO(){
        CustomerDTO result = new CustomerDTO();
        result.setId(this.id);
        result.setBirth(this.birth);
        result.setName(this.name);
        result.setStatus(this.status);
        return result;
    }

    public void builder(CustomerDTO dto){
        this.id = dto.getId();
        this.birth = dto.getBirth();
        this.name = dto.getName();
        this.status = dto.getStatus();
    }
}
