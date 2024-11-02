package br.com.dev.guzz.lcEstetica.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import br.com.dev.guzz.lcEstetica.models.SimpleApiRequest;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
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

@Entity(name = "customer_orders")
@Table(schema = "estetica", name = "customer_orders")
public class CustomerOrders extends SimpleApiRequest implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "sequence_order")
    private String sequenceOrder;

    @ManyToMany
    private List<Services> services;

    @Transient
    @JsonProperty(access = Access.WRITE_ONLY)
    private List<UUID> servicesId;

    @Transient
    private Clients client;

    @Column(name = "client_id", nullable = false)
    @JsonProperty(access = Access.WRITE_ONLY)
    private UUID clientId;

    @Column(name = "total_price", nullable = false)
    private BigDecimal totalPrice;

    @Column(name = "active")
    private Boolean active;

    @Column(name = "details", nullable = true)
    private String details;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
