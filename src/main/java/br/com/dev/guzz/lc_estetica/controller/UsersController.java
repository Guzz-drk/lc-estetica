package br.com.dev.guzz.lc_estetica.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.dev.guzz.lc_estetica.dto.ErroResponseDTO;
import br.com.dev.guzz.lc_estetica.dto.RegisterDTO;
import br.com.dev.guzz.lc_estetica.entity.Users;
import br.com.dev.guzz.lc_estetica.repository.UsersRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("users")
public class UsersController {
    
    @Autowired
    private UsersRepository usersRepository;

    @PostMapping("/register")
    public ResponseEntity register (@RequestBody @Valid RegisterDTO data){
        if(this.usersRepository.findByLogin(data.login()) != null) 
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErroResponseDTO("Este login já está em uso!"));

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());

        Users user = new Users(data.name().toUpperCase(), data.login(), encryptedPassword, data.mail(), data.role());

        this.usersRepository.save(user);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
