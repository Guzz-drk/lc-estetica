package br.com.dev.guzz.lcEstetica.useCases.customerOrders;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.dev.guzz.lcEstetica.entity.Clients;
import br.com.dev.guzz.lcEstetica.entity.CustomerOrders;
import br.com.dev.guzz.lcEstetica.entity.Services;
import br.com.dev.guzz.lcEstetica.entity.ServicesOrders;
import br.com.dev.guzz.lcEstetica.exceptions.CustomerOrdersException;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class GetCustomerOrderByIdUseCase {

    @Autowired
    private GenericUses genericUses;

    public ResponseEntity<?> execute(UUID id){
        try {
            CustomerOrders order = genericUses.findAndValidateCustomerOrder(id);

            Clients client = genericUses.findClient(order.getClientId());

            if(client != null)
                order.setClient(client);

            List<ServicesOrders> servicesOrders = genericUses.findServicesOrders(id); 
            List<Services> services = genericUses.mapToServices(servicesOrders);

            order.setServices(services);

            return ResponseEntity.status(HttpStatus.OK).body(order);
        } catch (Exception e) {
            log.error(CustomerOrdersException.class.getSimpleName() + ": Erro while finding by id : " + id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
