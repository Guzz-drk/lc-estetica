package br.com.dev.guzz.lc_estetica.dto;

import br.com.dev.guzz.lc_estetica.enums.UserRole;

public record RegisterDTO(String name, String login, String password, String mail, UserRole role) {
    
}
