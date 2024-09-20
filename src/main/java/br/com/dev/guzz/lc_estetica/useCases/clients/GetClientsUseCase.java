package br.com.dev.guzz.lc_estetica.useCases.clients;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.dev.guzz.lc_estetica.entity.Clients;
import br.com.dev.guzz.lc_estetica.repository.ClientsRepository;

@Service
public class GetClientsUseCase {
    
    @Autowired
    private ClientsRepository clientsRepository;

    public ResponseEntity<List<Clients>> execute(){
        
        List<Clients> clients = this.clientsRepository.findAll();

        return ResponseEntity.status(HttpStatus.OK).body(clients);
    }
}
