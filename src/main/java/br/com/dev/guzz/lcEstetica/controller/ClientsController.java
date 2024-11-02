package br.com.dev.guzz.lcEstetica.controller;

import java.util.List;
import java.util.UUID;

import br.com.dev.guzz.lcEstetica.models.SimpleApiRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.dev.guzz.lcEstetica.dto.ClientSearch;
import br.com.dev.guzz.lcEstetica.entity.Clients;
import br.com.dev.guzz.lcEstetica.useCases.clients.CreateClientUseCase;
import br.com.dev.guzz.lcEstetica.useCases.clients.GetClientByIdUseCase;
import br.com.dev.guzz.lcEstetica.useCases.clients.GetClientsBySearchUseCase;
import br.com.dev.guzz.lcEstetica.useCases.clients.GetClientsUseCase;
import jakarta.validation.Valid;

@RestController
@RequestMapping("clients")
public class ClientsController {
    
    @Autowired
    private CreateClientUseCase createClientUseCase;

    @Autowired
    private GetClientByIdUseCase getClientByIdUseCase;

    @Autowired
    private GetClientsUseCase getClientsUseCase;

    @Autowired
    private GetClientsBySearchUseCase getClientsBySearchUseCase;

    @PostMapping
    public ResponseEntity<?> createClient(@RequestBody @Valid Clients client){
        return createClientUseCase.execute(client);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getClientByID(@PathVariable(name = "id", required = true) UUID id, @RequestBody SimpleApiRequest request){
        return getClientByIdUseCase.execute(id);
    }

    @GetMapping
    public ResponseEntity<?> getClients(@RequestBody SimpleApiRequest request){
        return getClientsUseCase.execute();
    }

    @GetMapping("/search")
    public List<Clients> getClientsBySearch(@RequestHeader("search") String search, @RequestBody SimpleApiRequest request){
        return getClientsBySearchUseCase.execute(search);
    }
}
