package br.com.dev.guzz.lcEstetica.useCases.clients;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.dev.guzz.lcEstetica.entity.Clients;
import br.com.dev.guzz.lcEstetica.repository.ClientsRepository;

@Service
public class GetClientsUseCase {
    
    @Autowired
    private ClientsRepository clientsRepository;

    public ResponseEntity<List<Clients>> execute(){
        
        List<Clients> clients = this.clientsRepository.findAll();

        return ResponseEntity.status(HttpStatus.OK).body(clients);
    }
}
