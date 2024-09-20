package br.com.dev.guzz.lc_estetica.useCases.services;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.dev.guzz.lc_estetica.dto.ErroResponseDTO;
import br.com.dev.guzz.lc_estetica.entity.Services;
import br.com.dev.guzz.lc_estetica.exceptions.ServicesException;
import br.com.dev.guzz.lc_estetica.repository.CategoriesRepository;
import br.com.dev.guzz.lc_estetica.repository.ServicesRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class GetServiceByIdUseCase {
    
    @Autowired
    private ServicesRepository servicesRepository;

    @Autowired
    private CategoriesRepository categoriesRepository;

    public ResponseEntity execute(UUID id){
        try {
            Optional<Services> service = this.servicesRepository.findById(id);
            if(!service.isPresent()){
                log.info("Servico não encontrado, id recebido: ()", id);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErroResponseDTO("Serviço não encontrado!"));
            }
            Services serv = service.get();
            serv.setCategory(categoriesRepository.findById(serv.getCategoryId()).get());
            return ResponseEntity.status(HttpStatus.OK).body(serv);
        } catch (Exception e) {
            log.error(ServicesException.class.getSimpleName() + ": Error while finding by id: " + id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
