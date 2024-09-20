package br.com.dev.guzz.lc_estetica.entity;

import java.io.Serializable;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServicesOrdersId implements Serializable{
    
    private UUID serviceId;
    
    private UUID orderId;
}
