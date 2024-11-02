package br.com.dev.guzz.lcEstetica.useCases.clients;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.dev.guzz.lcEstetica.entity.Clients;
import br.com.dev.guzz.lcEstetica.repository.ClientsRepository;

@Service
public class GetClientsBySearchUseCase {
    
    @Autowired
    private ClientsRepository clientsRepository;

    public List<Clients> execute(String search){
        List<Clients> clients = clientsRepository.findByNameOrMailOrCpf(search);

        return clients.stream()
            .filter(cl -> cl.getActive() == Boolean.TRUE)
            .collect(Collectors.toList());
    }
}
