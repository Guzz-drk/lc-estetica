package br.com.dev.guzz.lc_estetica.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.dev.guzz.lc_estetica.dto.AuthenticationDTO;
import br.com.dev.guzz.lc_estetica.dto.LoginResponseDTO;
import br.com.dev.guzz.lc_estetica.entity.Users;
import br.com.dev.guzz.lc_estetica.infra.config.TokenService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("auth")
public class AuthenticationController {
    
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid AuthenticationDTO data){
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        Users user = (Users) auth.getPrincipal();
        var token = this.tokenService.generateToken(user);

        return ResponseEntity.ok(new LoginResponseDTO(user.getId(), user.getName(), token));
    }
}
