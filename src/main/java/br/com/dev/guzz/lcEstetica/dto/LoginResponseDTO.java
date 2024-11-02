package br.com.dev.guzz.lcEstetica.dto;

import java.util.UUID;

public record LoginResponseDTO(UUID id, String name, String token, int role) {
    
}
