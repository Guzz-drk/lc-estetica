package br.com.dev.guzz.lc_estetica.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import br.com.dev.guzz.lc_estetica.entity.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, UUID>{
    UserDetails findByLogin(String login);
}
