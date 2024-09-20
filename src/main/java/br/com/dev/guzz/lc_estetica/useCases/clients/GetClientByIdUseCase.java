package br.com.dev.guzz.lc_estetica.useCases.clients;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.dev.guzz.lc_estetica.dto.ErroResponseDTO;
import br.com.dev.guzz.lc_estetica.entity.Clients;
import br.com.dev.guzz.lc_estetica.exceptions.ClientsException;
import br.com.dev.guzz.lc_estetica.repository.ClientsRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class GetClientByIdUseCase {
    
    @Autowired
    private ClientsRepository clientsRepository;

    public ResponseEntity<?> execute(UUID id){
        try {
            Optional<Clients> client = clientsRepository.findById(id);
            if(client.isPresent())
                return ResponseEntity.status(HttpStatus.OK).body(client);
            else 
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErroResponseDTO("Cliente não encontrado!"));
        } catch (Exception e) {
            log.error(ClientsException.class.getSimpleName() + ": Erro while finding client", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
