package br.com.dev.guzz.lc_estetica.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.dev.guzz.lc_estetica.entity.Clients;
import br.com.dev.guzz.lc_estetica.useCases.clients.CreateClientUseCase;
import br.com.dev.guzz.lc_estetica.useCases.clients.GetClientByIdUseCase;
import br.com.dev.guzz.lc_estetica.useCases.clients.GetClientsUseCase;
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

    @PostMapping
    public ResponseEntity createClient(@RequestBody @Valid Clients client){
        return createClientUseCase.execute(client);
    }

    @GetMapping("/{id}")
    public ResponseEntity getClientByID(@PathVariable(name = "id", required = true) UUID id){
        return getClientByIdUseCase.execute(id);
    }

    @GetMapping
    public ResponseEntity getClients(){
        return getClientsUseCase.execute();
    }
}
