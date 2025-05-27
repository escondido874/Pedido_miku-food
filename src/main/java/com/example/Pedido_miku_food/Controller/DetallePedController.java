package com.example.Pedido_miku_food.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Pedido_miku_food.Service.DetallePedService;
import com.example.Pedido_miku_food.model.DetallePedido;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/v1/detalle")
public class DetallePedController {

    @Autowired
    private DetallePedService detallePedService;

    @PostMapping("/agregar")
    public DetallePedido agregarProd(@RequestBody DetallePedido prod) {
        return detallePedService.saveprod(prod);
    }

    @DeleteMapping("/eliminar")
    public String eliminarProd(@PathVariable Long id){
        detallePedService.eliminarPedido(id);
        return "Producto eliminado";
    }
    

}
