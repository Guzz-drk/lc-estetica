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

import br.com.dev.guzz.lc_estetica.entity.Services;
import br.com.dev.guzz.lc_estetica.useCases.services.CreateServiceUseCase;
import br.com.dev.guzz.lc_estetica.useCases.services.GetServiceByIdUseCase;
import br.com.dev.guzz.lc_estetica.useCases.services.GetServicesUseCase;
import jakarta.validation.Valid;

@RestController
@RequestMapping("services")
public class ServicesController {
    
    @Autowired
    private CreateServiceUseCase createServiceUseCase;

    @Autowired
    private GetServiceByIdUseCase getServiceByIdUseCase;

    @Autowired
    private GetServicesUseCase getServicesUseCase;

    @PostMapping
    public ResponseEntity createService(@RequestBody @Valid Services service){
        return this.createServiceUseCase.execute(service);
    }

    @GetMapping("/{id}")
    public ResponseEntity getServiceById(@PathVariable(name = "id") UUID id){
        return this.getServiceByIdUseCase.execute(id);
    }

    @GetMapping
    public ResponseEntity getServices(){
        return this.getServicesUseCase.execute();
    }
}
