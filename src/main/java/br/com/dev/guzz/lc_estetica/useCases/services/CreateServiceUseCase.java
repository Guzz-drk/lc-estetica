package br.com.dev.guzz.lc_estetica.useCases.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.dev.guzz.lc_estetica.dto.ErroResponseDTO;
import br.com.dev.guzz.lc_estetica.entity.Services;
import br.com.dev.guzz.lc_estetica.exceptions.ServicesException;
import br.com.dev.guzz.lc_estetica.repository.ServicesRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CreateServiceUseCase {
    
    @Autowired
    private ServicesRepository servicesRepository;

    public ResponseEntity execute(Services service){
        try {
            if((service.getCategoryId() == null) || (service.getCategoryId().toString().isEmpty()))
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErroResponseDTO("Categoria inválida!"));
            if(service.getPrice() == null)
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErroResponseDTO("O preço precisa ser preenchido!"));
            if(service.getActive() == null)
                service.setActive(true);
            this.servicesRepository.save(service);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            log.error(ServicesException.class.getSimpleName() + ": Error while creating services", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
