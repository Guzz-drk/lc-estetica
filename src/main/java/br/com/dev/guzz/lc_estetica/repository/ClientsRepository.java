package br.com.dev.guzz.lc_estetica.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.dev.guzz.lc_estetica.entity.Clients;
import java.util.Optional;


@Repository
public interface ClientsRepository extends JpaRepository<Clients, UUID>{
    
    Optional<Clients> findByCpf(String cpf);
}
