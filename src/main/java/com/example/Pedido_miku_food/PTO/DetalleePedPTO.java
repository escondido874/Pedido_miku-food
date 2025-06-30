package com.example.Pedido_miku_food.PTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetalleePedPTO {

    private Long id_detalle;

    private Long idProducto;

    private int cantidad;

    private int totalXProducto;

    private PedidoPTO pedido;


}
