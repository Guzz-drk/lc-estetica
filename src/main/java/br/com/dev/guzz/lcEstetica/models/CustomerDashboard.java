package br.com.dev.guzz.lcEstetica.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class CustomerDashboard {

    private Long qtdOpenedOrders;

    private Long qtdClosedOrders;
}
