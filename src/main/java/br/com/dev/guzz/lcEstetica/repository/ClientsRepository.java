package br.com.dev.guzz.lcEstetica.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.dev.guzz.lcEstetica.entity.Clients;

import java.util.List;
import java.util.Optional;


@Repository
public interface ClientsRepository extends JpaRepository<Clients, UUID>{
    
    Optional<Clients> findByCpf(String cpf);

    @Query(nativeQuery = true, value = "select c.* from estetica.clients c " +
                "where (c.name is null or unaccent(c.name)  ilike concat('%', unaccent( :pesquisa), '%') " +
                "or c.cpf is null or unaccent(c.cpf) ilike concat('%', unaccent( :pesquisa), '%') " +
                "or c.mail is null or unaccent(c.mail) ilike concat('%', unaccent( :pesquisa), '%') ) " +
                "and c.active = true ")
    List<Clients> findByNameOrMailOrCpf(String pesquisa);
}
