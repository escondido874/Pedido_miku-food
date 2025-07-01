package com.example.Pedido_miku_food.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Pedido_miku_food.Service.DetallePedService;
import com.example.Pedido_miku_food.model.DetallePedido;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/v1/detalle")
@Tag(name = "DetallePedidos", description = "Operaciones relacionadas con los detalles de pedidos de Miku-Food")
public class DetallePedController {

    @Autowired
    private DetallePedService detallePedService;

    @PostMapping("/agregar")
    @Operation(summary = "Agregar un producto al detalle del pedido", description = "Agrega un nuevo producto al detalle de un pedido en Miku-Food")
    public DetallePedido agregarProd(@RequestBody DetallePedido prod) {
        return detallePedService.saveprod(prod);
    }

    @DeleteMapping("/eliminar")
    @Operation(summary = "Eliminar un producto del detalle del pedido", description = "Elimina un producto específico del detalle de un pedido en Miku-Food")
    public String eliminarProd(@PathVariable Long id){
        detallePedService.eliminarPedido(id);
        return "Producto eliminado";
    }
    

}
