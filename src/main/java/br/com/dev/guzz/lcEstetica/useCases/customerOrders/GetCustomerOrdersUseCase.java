package br.com.dev.guzz.lcEstetica.useCases.customerOrders;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.dev.guzz.lcEstetica.entity.Clients;
import br.com.dev.guzz.lcEstetica.entity.CustomerOrders;
import br.com.dev.guzz.lcEstetica.entity.Services;
import br.com.dev.guzz.lcEstetica.entity.ServicesOrders;
import br.com.dev.guzz.lcEstetica.repository.CustomerOrdersRepository;

@Service
public class GetCustomerOrdersUseCase {
    
    @Autowired
    private CustomerOrdersRepository customerOrdersRepository;

    @Autowired
    private GenericUses genericUses;

    public ResponseEntity<?> execute(){
        List<CustomerOrders> orders = customerOrdersRepository.findAll();
        orders.stream().forEach(order -> {
            Clients client = genericUses.findClient(order.getClientId());
            
            if(client != null)
                order.setClient(client);
            
            List<Services> services = new ArrayList<>();
            List<ServicesOrders> servicesOrders = genericUses.findServicesOrders(order.getId());
            if(!servicesOrders.isEmpty())
                services = genericUses.mapToServices(servicesOrders);
        
            order.setServices(services);
        });
        return ResponseEntity.status(HttpStatus.OK).body(orders);
    }
}
