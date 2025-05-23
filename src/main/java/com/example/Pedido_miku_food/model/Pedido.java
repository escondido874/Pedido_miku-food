package com.example.Pedido_miku_food.model;

import java.util.Date;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "pedido")
@Entity
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Date fecha_pedido;

    @Column(nullable = false, length = 1)
    private int tipoEntrega;

    @Column(nullable = false)
    private double total;

    @Column(nullable = true, length = 50)
    private String direccion;

    @Column(nullable = false)
    private Long id_cliente;

    @Column(nullable = false)
    private Long id_detalle;
}
