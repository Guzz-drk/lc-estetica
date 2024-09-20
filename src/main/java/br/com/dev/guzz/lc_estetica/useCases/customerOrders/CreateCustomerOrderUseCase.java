package br.com.dev.guzz.lc_estetica.useCases.customerOrders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.dev.guzz.lc_estetica.entity.CustomerOrders;
import br.com.dev.guzz.lc_estetica.entity.Services;
import br.com.dev.guzz.lc_estetica.entity.ServicesOrders;
import br.com.dev.guzz.lc_estetica.exceptions.CustomerOrdersException;
import br.com.dev.guzz.lc_estetica.repository.CustomerOrdersRepository;
import br.com.dev.guzz.lc_estetica.useCases.services.GetServiceByIdUseCase;
import br.com.dev.guzz.lc_estetica.useCases.servicesOrders.CreateServicesOrderUseCase;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CreateCustomerOrderUseCase {
    
    @Autowired
    private CustomerOrdersRepository customerOrdersRepository;

    @Autowired
    private CreateServicesOrderUseCase createServicesOrderUseCase;

    @Autowired
    private GetServiceByIdUseCase getServiceByIdUseCase;

    public ResponseEntity execute(CustomerOrders cOrder){
        try {
            if(cOrder.getActive() == null)
                cOrder.setActive(true);
            Long value = customerOrdersRepository.getSequenceValue();
            cOrder.setSequenceOrder("ORDEM " + value);
            CustomerOrders cOrderCreated = this.customerOrdersRepository.save(cOrder);
            cOrder.getServicesId().stream().forEach(id -> {
                Services service = (Services) getServiceByIdUseCase.execute(id).getBody();
                ServicesOrders sOrder = ServicesOrders.builder()
                    .orderId(cOrderCreated.getId())
                    .serviceId(id)
                    .order(cOrderCreated)
                    .service(service)
                    .build();
                createServicesOrderUseCase.execute(sOrder);
            });
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            log.error(CustomerOrdersException.class.getSimpleName() + ": Error while creating customer order", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
