package br.com.dev.guzz.lc_estetica.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(of = "id")

@Entity(name = "clients")
@Table(schema = "estetica", name = "clients")
public class Clients {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "name", nullable = false)
    @NotEmpty(message = "Name cannot be null or empty")
    private String name;

    @Column(name = "cpf", nullable = true)
    private String cpf;

    @Column(name = "fone_number_area_code", nullable = true)
    private String foneNumberAreaCode;

    @Column(name = "fone_number", nullable = true)
    private String foneNumber;

    @Column(name = "mail", nullable = true)
    private String mail;

    @Column(name = "active")
    private Boolean active;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
