package br.com.dev.guzz.lcEstetica.dto;

import br.com.dev.guzz.lcEstetica.enums.UserRole;

public record RegisterDTO(String name, String login, String password, String mail, UserRole role) {
    
}
