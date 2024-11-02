package br.com.dev.guzz.lcEstetica.useCases.services;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.dev.guzz.lcEstetica.dto.ErroResponseDTO;
import br.com.dev.guzz.lcEstetica.entity.Services;
import br.com.dev.guzz.lcEstetica.exceptions.ServicesException;
import br.com.dev.guzz.lcEstetica.repository.CategoriesRepository;
import br.com.dev.guzz.lcEstetica.repository.ServicesRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class GetServiceByIdUseCase {
    
    @Autowired
    private ServicesRepository servicesRepository;

    @Autowired
    private CategoriesRepository categoriesRepository;

    public ResponseEntity<?> execute(UUID id){
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
