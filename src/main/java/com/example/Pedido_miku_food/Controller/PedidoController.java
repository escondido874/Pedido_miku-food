package com.example.Pedido_miku_food.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Pedido_miku_food.Service.PedidoService;
import com.example.Pedido_miku_food.model.Pedido;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/api/v1/pedido")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @GetMapping()
    public ResponseEntity<List<Pedido>> listar(){
        List<Pedido> listaped=pedidoService.listarPedidos();
        if (listaped.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(listaped);
    }
    @PostMapping("agregar")
    public Pedido agregarPedido(@RequestBody Pedido newpedido) {
        return pedidoService.agregarPedido(newpedido);
    }
    
    @GetMapping("/buscarid/{id}")
    public ResponseEntity<Optional<Pedido>> buscarid(@PathVariable Long id) {
        try {
            Optional<Pedido> pedido = pedidoService.buscarXid(id);
            return ResponseEntity.ok(pedido);
        } catch (Exception e ) {
            return ResponseEntity.notFound().build();
        }
    }

        @GetMapping("/buscarUser/{id}")
    public ResponseEntity<List<Pedido>> buscarxusr(@PathVariable Long id) {
        List<Pedido> pedidosuser= pedidoService.buscarXUser(id);
        if (pedidosuser.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(pedidosuser);
    }

        @PutMapping("/actualizar/{id}")
    public ResponseEntity<Pedido> actualizar(@PathVariable Long id, @RequestBody Pedido pedido) {
        Optional<Pedido> pedOptional = pedidoService.buscarXid(id);
        
        try {
            Pedido ped =pedOptional.get();
            ped.setDireccion(pedido.getDireccion());
            ped.setTipoEntrega(pedido.getTipoEntrega());
            ped.setFecha_pedido(pedido.getFecha_pedido());
            ped.setTipoEntrega(pedido.getTipoEntrega());
            ped.setDetalles(pedido.getDetalles());

            pedidoService.agregarPedido(ped);
            return ResponseEntity.ok(pedido);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/eliminar/{id}")
     public ResponseEntity<String> eliminarped(@PathVariable Long id) {
        
        try {
            pedidoService.eliminarPedido(id);
            return ResponseEntity.ok("Pedido Eliminado");  
        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }
    

}
