package com.example.Pedido_miku_food.PTO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PedidoPTO {

    private Long id_pedido;
   
    private Date fecha_pedido;

    private int tipoEntrega;

    private double total;

    private String direccion;

    private Long idUsuario;
    
    private List<DetalleePedPTO> detalles = new ArrayList<>();


}
