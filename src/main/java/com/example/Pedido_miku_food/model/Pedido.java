package com.example.Pedido_miku_food.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "pedido")
@Entity
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_pedido;

    @Column(nullable = false)
    private Date fecha_pedido;

    @Column(nullable = false, length = 1)
    private int tipoEntrega;

    @Column(nullable = false)
    private double total;

    @Column(nullable = true, length = 50)
    private String direccion;

    @Column(nullable = false)
    private Long idUsuario;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<DetallePedido> detalles = new ArrayList<>();

}
