package br.com.dev.guzz.lc_estetica.useCases.clients;

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
public class CreateClientUseCase {
    
    @Autowired
    private ClientsRepository clientsRepository;

    public ResponseEntity execute(Clients client){
        try {
            if((client.getCpf() != null) && (!client.getCpf().isEmpty())){
                String cpf = client.getCpf().replaceAll("\\.", "").replaceAll("-", "");
                client.setCpf(cpf);
                if(this.clientsRepository.findByCpf(cpf).isPresent()){
                    log.info(ClientsException.class.getSimpleName() + ": CPF existente para outro cliente");
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErroResponseDTO("CPF existente em outro cliente!"));
                }
            } else {
                client.setCpf(null);
            }
            if(client.getActive() == null) 
                client.setActive(true);
            client.setName(client.getName().toUpperCase());
            clientsRepository.save(client);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            log.error(ClientsException.class.getSimpleName() + ": Error while creating client", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
