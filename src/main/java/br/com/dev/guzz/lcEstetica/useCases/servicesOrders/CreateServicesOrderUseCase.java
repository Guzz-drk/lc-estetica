package br.com.dev.guzz.lcEstetica.useCases.servicesOrders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.dev.guzz.lcEstetica.entity.ServicesOrders;
import br.com.dev.guzz.lcEstetica.repository.ServicesOrdersRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CreateServicesOrderUseCase {
    
    @Autowired
    private ServicesOrdersRepository servicesOrdersRepository;

    public void execute(ServicesOrders sOrder){
        try {
            this.servicesOrdersRepository.save(sOrder);
        } catch (Exception e) {
            log.error("Error while creating services order", e);
        }
    }
}
