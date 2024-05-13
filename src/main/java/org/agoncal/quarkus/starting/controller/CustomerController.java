package org.agoncal.quarkus.starting.controller;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.agoncal.quarkus.starting.dto.CustomerDTO;
import org.agoncal.quarkus.starting.dto.CommonListDTO;
import org.agoncal.quarkus.starting.dto.DataFilterDTO;
import org.agoncal.quarkus.starting.entity.Customer;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@Path("/api/customers")
@Produces(MediaType.APPLICATION_JSON)
public class CustomerController {

    @POST
    @Transactional
    public Response  create(CustomerDTO CustomerDTO) {
        Customer Customer = new Customer();
        Customer.builder(CustomerDTO);
        Customer.persist();
        CustomerDTO = Customer.builderDTO();
        Response.ResponseBuilder response = Response.created(URI.create("/api/Customers/" + Customer.id));
        response.entity(CustomerDTO);
        return response.build();
    }
    @GET
    @Path("{id}")
    public CustomerDTO getCustomer(@PathParam("id") Long id){
        Customer customer = Customer.findById(id);
        if(customer == null){
            return null;
        }
        return customer.builderDTO();
    }

    @GET
    public CommonListDTO<CustomerDTO> getAllCustomers(@BeanParam DataFilterDTO dataFilterDTO){
        if(dataFilterDTO == null){
            return null;
        }
        int page = dataFilterDTO.getPage();
        int pageSize = dataFilterDTO.getSize();
        int fromIndex = (page - 1) * pageSize;

        List<Customer> Customers;
        PanacheQuery<Customer> query;

        if(dataFilterDTO.getKeyWords() == null || dataFilterDTO.getKeyWords().isEmpty()){
            query = Customer.findAll();
        }else{
            query = Customer.find("title =?1", dataFilterDTO.getKeyWords());
        }
        long total = query.count();
        int toIndex = Math.min((int)total, page * pageSize);
       // query.range(fromIndex, toIndex );
        Customers = query.list();

        List<Customer> result = Customers.subList(fromIndex, toIndex);
        List<CustomerDTO> CustomerDTOs = result.stream().map(Customer::builderDTO).collect(Collectors.toList());

        CommonListDTO<CustomerDTO> commonListDTO = new CommonListDTO<>();
        commonListDTO.setTotal((int)total);
        commonListDTO.setPage(page);
        commonListDTO.setSize(pageSize);
        commonListDTO.setValues(CustomerDTOs);

        return commonListDTO;
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public Response delete(@PathParam("id") int id){
        Customer customer = Customer.findById(id);
        if(customer == null){
            return null;
        }
        customer.delete();
        return Response.noContent().build();
    }

}
