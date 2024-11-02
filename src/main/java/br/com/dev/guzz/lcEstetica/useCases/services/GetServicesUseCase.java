package br.com.dev.guzz.lcEstetica.useCases.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.dev.guzz.lcEstetica.entity.Services;
import br.com.dev.guzz.lcEstetica.exceptions.ServicesException;
import br.com.dev.guzz.lcEstetica.repository.CategoriesRepository;
import br.com.dev.guzz.lcEstetica.repository.ServicesRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class GetServicesUseCase {
    
    @Autowired
    private ServicesRepository servicesRepository;

    @Autowired
    private CategoriesRepository categoriesRepository;

    public ResponseEntity<?> execute(){
        try {
            List<Services> services = this.servicesRepository.findAll();
            services.stream().forEach(serv -> serv.setCategory(categoriesRepository.findById(serv.getCategoryId()).get()));
            return ResponseEntity.status(HttpStatus.OK).body(services);
        } catch (Exception e) {
            log.error(ServicesException.class.getSimpleName() + ": Error while finding all services", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
