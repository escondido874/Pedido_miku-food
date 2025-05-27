package com.example.Pedido_miku_food.Service;

import java.util.List;
import java.util.Optional;

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

    public Optional<Pedido> buscarXid(Long idPedido){
        return pedidoRepository.findById(idPedido);
    }

    public Pedido agregarPedido(Pedido nuevoPedido){
        return pedidoRepository.save(nuevoPedido);
    }

    public void eliminarPedido(Long idPedido){
        pedidoRepository.deleteById(idPedido);
    }
    
    public List<Pedido> buscarXUser(Long idUser){
        return pedidoRepository.findById_usuario(idUser);
    }


}
