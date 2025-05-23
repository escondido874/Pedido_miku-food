package com.example.Pedido_miku_food.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Pedido_miku_food.model.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido,Long>{

}
