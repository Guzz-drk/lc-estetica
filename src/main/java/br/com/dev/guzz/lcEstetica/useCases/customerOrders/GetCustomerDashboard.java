package br.com.dev.guzz.lcEstetica.useCases.customerOrders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.dev.guzz.lcEstetica.models.CustomerDashboard;
import br.com.dev.guzz.lcEstetica.repository.CustomerOrdersRepository;

@Service
public class GetCustomerDashboard {

    @Autowired
    private CustomerOrdersRepository customerOrdersRepository;
    
    public CustomerDashboard execute(){
        
        Long openedOrders = customerOrdersRepository.countByActive(true);
        Long closedOrders = customerOrdersRepository.countByActive(false);

        return CustomerDashboard.builder()
            .qtdOpenedOrders(openedOrders)
            .qtdClosedOrders(closedOrders)
            .build();
    }
}
