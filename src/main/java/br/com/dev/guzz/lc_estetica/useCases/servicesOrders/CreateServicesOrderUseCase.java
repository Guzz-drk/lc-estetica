package br.com.dev.guzz.lc_estetica.useCases.servicesOrders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.dev.guzz.lc_estetica.entity.ServicesOrders;
import br.com.dev.guzz.lc_estetica.repository.ServicesOrdersRepository;
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
