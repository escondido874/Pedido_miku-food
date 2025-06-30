package com.example.Pedido_miku_food.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.Pedido_miku_food.model.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido,Long>{


    //Obtener pedidos segun usuario
    @Query(value = "SELECT * FROM pedido WHERE v_idUsuario = :idUsuario", nativeQuery = true)
    List<Pedido> findById_usuario(Long v_idUsuario);

}
