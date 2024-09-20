package br.com.dev.guzz.lc_estetica.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
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

@Entity(name = "services")
@Table(schema = "estetica", name = "services")
public class Services {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "description", nullable = false)
    @NotEmpty(message = "Description cannot be null or empty")
    private String description;

    @Column(name = "details", nullable = true)
    private String details;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Column(name = "active")
    private Boolean active;

    @Column(name = "category_id", nullable = false)
    @JsonProperty(access = Access.WRITE_ONLY)
    private UUID categoryId;

    @Transient
    private Categories category;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
