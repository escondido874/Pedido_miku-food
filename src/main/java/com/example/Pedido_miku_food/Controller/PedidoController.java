package com.example.Pedido_miku_food.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Pedido_miku_food.PTO.PedidoPTO;
import com.example.Pedido_miku_food.Service.PedidoService;
import com.example.Pedido_miku_food.model.Pedido;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/api/v1/pedido")
@Tag(name = "Pedidos", description = "Operaciones relacionadas con los pedidos de Miku-Food")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @GetMapping()
    @Operation(summary = "Listar todos los pedidos", description = "Obtiene una lista de todos los pedidos realizados en Miku-Food")
    @ApiResponse(responseCode = "200", description = "Lista de pedidos obtenida exitosamente")
    @ApiResponse(responseCode = "204", description = "La lista se encuentra vacía (No hay pedidos)")
    @Schema(description = "Lista de pedidos", implementation = PedidoPTO.class)
    public ResponseEntity<List<PedidoPTO>> listar(){
        List<PedidoPTO> listaped=pedidoService.listarPedidos();
        if (listaped.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(listaped);
    }

    @PostMapping("agregar")
    @Operation(summary = "Agregar un nuevo pedido", description = "Crea un nuevo pedido en el sistema de Miku-Food")
    @Schema(description = "Pedido a agregar", implementation = Pedido.class)
    @ApiResponse(responseCode = "201", description = "Pedido creado exitosamente")
    public ResponseEntity<?> agregarPedido(@RequestBody Pedido newpedido) {
        if (!validarPedido(newpedido)) {
            return ResponseEntity.badRequest().body("Datos de pedido inválidos");
        }
        Pedido pedidoGuardado = pedidoService.agregarPedido(newpedido);
        return ResponseEntity.status(201).body(pedidoGuardado);
    }
    
    @GetMapping("/buscarid/{id}")
    @Operation(summary = "Buscar pedido por ID", description = "Obtiene un pedido específico por su ID")
    @ApiResponse(responseCode = "200", description = "Pedido encontrado exitosamente")
    @ApiResponse(responseCode = "404", description = "Pedido no encontrado")
    public ResponseEntity<Optional<Pedido>> buscarid(@PathVariable Long id) {
        try {
            Optional<Pedido> pedido = pedidoService.buscarXid(id);
            return ResponseEntity.ok(pedido);
        } catch (Exception e ) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/buscarUser/{id}")
    @Operation(summary = "Buscar pedidos por usuario", description = "Obtiene una lista de pedidos realizados por un usuario específico")
    @ApiResponse(responseCode = "200", description = "Pedidos del usuario encontrados exitosamente")
    @ApiResponse(responseCode = "404", description = "No se encontraron pedidos para el usuario")
    public ResponseEntity<List<Pedido>> buscarxusr(@PathVariable Long id) {
        List<Pedido> pedidosuser= pedidoService.buscarXUser(id);
        if (pedidosuser.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(pedidosuser);
    }

    @PutMapping("/actualizar/{id}")
    @Operation(summary = "Actualizar un pedido", description = "Actualiza un pedido existente en el sistema de Miku-Food")
    @ApiResponse(responseCode = "200", description = "Pedido actualizado exitosamente")
    @ApiResponse(responseCode = "404", description = "Pedido no encontrado")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody Pedido pedido) {
        if (!validarPedido(pedido)) {
            return ResponseEntity.badRequest().body("Datos de pedido inválidos");
        }
        Optional<Pedido> pedOptional = pedidoService.buscarXid(id);
        try {
            Pedido ped = pedOptional.get();
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
    @Operation(summary = "Eliminar un pedido", description = "Elimina un pedido existente en el sistema de Miku-Food")
    @ApiResponse(responseCode = "200", description = "Pedido eliminado exitosamente")
    @ApiResponse(responseCode = "404", description = "Pedido no encontrado")
    public ResponseEntity<String> eliminarped(@PathVariable Long id) {

        try {
            pedidoService.eliminarPedido(id);
            return ResponseEntity.ok("Pedido Eliminado");  
        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }
    

    private boolean validarPedido(Pedido pedido){
        // Valida que los campos principales del pedido no sean nulos
        if (pedido == null) {
            return false;
        }
        // Si el tipo de entrega es 1 (retiro en local), la dirección puede ser nula o vacía
        if (pedido.getTipoEntrega() == 1) {
            // No se valida dirección
        } else if (pedido.getTipoEntrega() == 2) {
            // Si el tipo de entrega es 2 (envío a domicilio), la dirección es obligatoria
            if (pedido.getDireccion() == null || pedido.getDireccion().trim().isEmpty()) {
                return false;
            }
        } else {
            // Si el tipo de entrega no es 1 ni 2, no es válido
            return false;
        }
        if (pedido.getFecha_pedido() == null) {
            return false;
        }
        if (pedido.getIdUsuario() == null) {
            return false;
        }
       
        if (pedido.getTotal() < 0) {
            return false;
        }
        
        return true;
    }
    

}
