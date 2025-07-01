package com.example.Pedido_miku_food.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Pedido_miku_food.Repository.DetallePedRepository;
import com.example.Pedido_miku_food.Repository.PedidoRepository;
import com.example.Pedido_miku_food.model.DetallePedido;
import com.example.Pedido_miku_food.model.Pedido;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class DetallePedService {

    @Autowired
    private DetallePedRepository detallePedRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    public DetallePedido saveprod(DetallePedido product){
        // Guardar el detalle
        DetallePedido detalleGuardado = detallePedRepository.save(product);
        // Obtener el pedido asociado
        Pedido pedido = pedidoRepository.findById(detalleGuardado.getPedido().getId_pedido()).orElse(null);
        if (pedido != null) {
            // multiplicar cantidad con el totalXProducto del detalle y sumarlo al total del pedido
            pedido.setTotal(pedido.getTotal() + (detalleGuardado.getTotalXProducto()*detalleGuardado.getCantidad()));
            pedidoRepository.save(pedido);
        }
        return detalleGuardado;
    }


    public void eliminarPedido(Long idProd){
        detallePedRepository.deleteById(idProd);
    }
    
}
