package com.example.Pedido_miku_food.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Pedido_miku_food.Repository.DetallePedRepository;
import com.example.Pedido_miku_food.model.DetallePedido;


import jakarta.transaction.Transactional;

@Service
@Transactional
public class DetallePedService {

    @Autowired
    private DetallePedRepository detallePedRepository;

    public DetallePedido saveprod(DetallePedido product){
        return detallePedRepository.save(product);
    }


    public void eliminarPedido(Long idProd){
        detallePedRepository.deleteById(idProd);
    }
    
}
