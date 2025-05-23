package com.example.Pedido_miku_food.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Pedido_miku_food.Repository.PedidoRepository;
import com.example.Pedido_miku_food.model.Pedido;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    public List<Pedido> listarPedidos(){
        return pedidoRepository.findAll();
    }

    public Pedido agregarPedido(Pedido nuevoPedido){
        return pedidoRepository.save(nuevoPedido);
    }
}
