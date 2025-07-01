package com.example.Pedido_miku_food.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Pedido_miku_food.PTO.DetalleePedPTO;
import com.example.Pedido_miku_food.PTO.PedidoPTO;
import com.example.Pedido_miku_food.Repository.PedidoRepository;
import com.example.Pedido_miku_food.model.DetallePedido;
import com.example.Pedido_miku_food.model.Pedido;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    // Lista todos los pedidos y los transforma a PedidoPTO
    public List<PedidoPTO> listarPedidos(){
        List<Pedido> pedidos = pedidoRepository.findAll();
        List<PedidoPTO> pedpto= new ArrayList<>();
        for (Pedido pedido : pedidos) {
            List<DetalleePedPTO> detpt =new ArrayList<>();
            PedidoPTO pedtemp = new PedidoPTO();
            pedtemp.setDireccion(pedido.getDireccion());
            pedtemp.setFecha_pedido(pedido.getFecha_pedido());
            pedtemp.setIdUsuario(pedido.getIdUsuario());
            pedtemp.setId_pedido(pedido.getId_pedido());
            pedtemp.setTipoEntrega(pedido.getTipoEntrega());
            pedtemp.setTotal(pedido.getTotal());
            for (DetallePedido det : pedido.getDetalles()) {
                DetalleePedPTO detTemp = new DetalleePedPTO();
                detTemp.setCantidad(det.getCantidad());
                detTemp.setIdProducto(det.getIdProducto());
                detTemp.setId_detalle(det.getId_detalle());
                detTemp.setTotalXProducto(det.getTotalXProducto());
                detpt.add(detTemp);
            }
            pedtemp.setDetalles(detpt);
            pedpto.add(pedtemp);
        }
        return pedpto;
    }

    // Busca un pedido por su ID
    public Optional<Pedido> buscarXid(Long idPedido){
        return pedidoRepository.findById(idPedido);
    }

    // Guarda un nuevo pedido
    public Pedido agregarPedido(Pedido nuevoPedido){
        return pedidoRepository.save(nuevoPedido);
    }

    // Elimina un pedido por su ID
    public void eliminarPedido(Long idPedido){
        pedidoRepository.deleteById(idPedido);
    }
    
    // Busca pedidos por usuario
    public List<Pedido> buscarXUser(Long idUser){
        return pedidoRepository.findById_usuario(idUser);
    }


}
