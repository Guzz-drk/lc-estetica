package br.com.dev.guzz.lcEstetica.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.dev.guzz.lcEstetica.entity.CustomerOrders;
import br.com.dev.guzz.lcEstetica.models.CustomerDashboard;
import br.com.dev.guzz.lcEstetica.useCases.customerOrders.CreateCustomerOrderUseCase;
import br.com.dev.guzz.lcEstetica.useCases.customerOrders.GetCustomerDashboard;
import br.com.dev.guzz.lcEstetica.useCases.customerOrders.GetCustomerOrderByIdUseCase;
import br.com.dev.guzz.lcEstetica.useCases.customerOrders.GetCustomerOrdersUseCase;
import jakarta.validation.Valid;

@RestController
@RequestMapping("customerOrders")
public class CustomerOrdersController {
    
    @Autowired
    private CreateCustomerOrderUseCase createCustomerOrderUseCase;

    @Autowired
    private GetCustomerOrderByIdUseCase getCustomerOrderByIdUseCase;

    @Autowired
    private GetCustomerOrdersUseCase getCustomerOrdersUseCase;

    @Autowired
    private GetCustomerDashboard getCustomerDashboard;

    @PostMapping
    public ResponseEntity<?> createCustomerOrder(@RequestBody @Valid CustomerOrders cOrder){
        return this.createCustomerOrderUseCase.execute(cOrder);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCustomerOrderById(@PathVariable(name = "id") UUID id){
        return this.getCustomerOrderByIdUseCase.execute(id);
    }

    @GetMapping
    public ResponseEntity<?> getCustomerOrder(){
        return this.getCustomerOrdersUseCase.execute();
    }

    @GetMapping("/dashboard")
    public CustomerDashboard getDashboard(){
        return this.getCustomerDashboard.execute();
    }
}
