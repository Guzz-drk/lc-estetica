package br.com.dev.guzz.lc_estetica.useCases.customerOrders;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.dev.guzz.lc_estetica.entity.Clients;
import br.com.dev.guzz.lc_estetica.entity.CustomerOrders;
import br.com.dev.guzz.lc_estetica.entity.Services;
import br.com.dev.guzz.lc_estetica.entity.ServicesOrders;
import br.com.dev.guzz.lc_estetica.exceptions.CustomerOrdersException;
import br.com.dev.guzz.lc_estetica.repository.CustomerOrdersRepository;
import br.com.dev.guzz.lc_estetica.repository.ServicesOrdersRepository;
import br.com.dev.guzz.lc_estetica.useCases.clients.GetClientByIdUseCase;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class GetCustomerOrderByIdUseCase {

    @Autowired
    private GenericUses genericUses;

    public ResponseEntity execute(UUID id){
        try {
            CustomerOrders order = genericUses.findAndValidateCustomerOrder(id);

            Clients client = genericUses.findClient(order.getClientId());
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
