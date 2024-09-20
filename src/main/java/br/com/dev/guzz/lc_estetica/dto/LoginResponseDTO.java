package br.com.dev.guzz.lc_estetica.dto;

import java.util.UUID;

public record LoginResponseDTO(UUID id, String name, String token) {
    
}
