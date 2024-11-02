package br.com.dev.guzz.lcEstetica.models;

import java.math.BigDecimal;
import java.util.UUID;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
public class ServiceCategory {

    @Id
    private UUID serviceId;

    private String serviceDescription;

    private String serviceDetails;

    private BigDecimal servicePrice;

    private Boolean serviceActive;

    private LocalDateTime serviceCreated;

    private LocalDateTime serviceUpdated;

    private UUID categoryId;

    private String categoryDescription;

    private String categoryDetails;

    private Boolean categoryActive;

    private LocalDateTime categoryCreated;

    private LocalDateTime categoryUpdated;
}
