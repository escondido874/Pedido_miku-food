package com.example.Pedido_miku_food.controllertest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.Pedido_miku_food.Controller.PedidoController;
import com.example.Pedido_miku_food.PTO.PedidoPTO;
import com.example.Pedido_miku_food.Service.PedidoService;
import com.example.Pedido_miku_food.model.Pedido;

public class PedidoControllerTest {

    @Mock
    private PedidoService pedidoService;

    @InjectMocks
    private PedidoController pedidoController;
    private Pedido pedido;
    private List<Pedido> listaPedidos;

    // Método que se ejecuta antes de cada test para inicializar los mocks y los datos de prueba
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        pedido = new Pedido(); // Crea un pedido de prueba
        pedido.setId_pedido(1L);
        pedido.setFecha_pedido(new Date());
        pedido.setTipoEntrega(1);
        pedido.setTotal(100.50);
        pedido.setDireccion("Calle Falsa 123");
        pedido.setIdUsuario(1L);

        listaPedidos = Arrays.asList(pedido);
    }

    // Test para listar pedidos usando el controlador
    @Test
    public void testlistarpedido(){
        PedidoPTO pedido = PedidoPTO.builder()
            .fecha_pedido(new Date())
            .tipoEntrega(1)
            .total(125.99)
            .direccion("Calle Principal 123")
            .idUsuario(456L)
            .build();
        when(pedidoService.listarPedidos()).thenReturn(Arrays.asList(pedido)); // Simula la respuesta del servicio
        ResponseEntity<List<PedidoPTO>> result = pedidoController.listar(); // Llama al método del controlador
        assertEquals(HttpStatus.OK, result.getStatusCode()); // Verifica el status
        assertEquals(1, result.getBody().size()); // Verifica la cantidad de elementos
    }
    
    // Test para agregar un pedido usando el controlador
    @Test
    void testAgregarPedido() {
        when(pedidoService.agregarPedido(any(Pedido.class))).thenReturn(pedido); 
        ResponseEntity<?> respuesta = pedidoController.agregarPedido(pedido); 
        assertEquals(HttpStatus.CREATED, respuesta.getStatusCode());
        Pedido resultado = (Pedido) respuesta.getBody();
        assertNotNull(resultado); 
        assertEquals(pedido.getId_pedido(), resultado.getId_pedido()); 
        verify(pedidoService, times(1)).agregarPedido(any(Pedido.class)); 
    }

    // Test para buscar un pedido por id existente
    @Test
    void testBuscarId_Existente() {
        when(pedidoService.buscarXid(1L)).thenReturn(Optional.of(pedido)); 
        ResponseEntity<Optional<Pedido>> respuesta = pedidoController.buscarid(1L); 
        assertEquals(HttpStatus.OK, respuesta.getStatusCode()); 
        assertTrue(respuesta.getBody().isPresent()); 
        assertEquals(pedido.getId_pedido(), respuesta.getBody().get().getId_pedido()); 
    }

    // Test para buscar pedidos por usuario con resultados
    @Test
    void testBuscarPorUsuario_ConResultados() {
        when(pedidoService.buscarXUser(1L)).thenReturn(listaPedidos); 
        ResponseEntity<List<Pedido>> respuesta = pedidoController.buscarxusr(1L); 
        assertEquals(HttpStatus.OK, respuesta.getStatusCode()); 
        assertFalse(respuesta.getBody().isEmpty()); 
        assertEquals(1, respuesta.getBody().size()); 
    }

    // Test para actualizar un pedido existente
    @Test
    void testActualizarPedido_Existente() {
        Pedido pedidoActualizado = new Pedido();
        pedidoActualizado.setDireccion("Nueva Dirección 456");
        pedidoActualizado.setTipoEntrega(1); // Valor válido
        pedidoActualizado.setFecha_pedido(new Date());
        pedidoActualizado.setIdUsuario(1L);
        pedidoActualizado.setTotal(100.0);
        when(pedidoService.buscarXid(1L)).thenReturn(Optional.of(pedido)); 
        when(pedidoService.agregarPedido(any(Pedido.class))).thenReturn(pedidoActualizado); 
        ResponseEntity<?> respuesta = pedidoController.actualizar(1L, pedidoActualizado); 
        assertEquals(HttpStatus.OK, respuesta.getStatusCode()); 
        Pedido resultado = (Pedido) respuesta.getBody();
        assertEquals("Nueva Dirección 456", resultado.getDireccion()); 
    }

    // Test para eliminar un pedido existente
    @Test
    void testEliminarPedido_Existente() {
        doNothing().when(pedidoService).eliminarPedido(1L); 
        ResponseEntity<String> respuesta = pedidoController.eliminarped(1L); 
        assertEquals(HttpStatus.OK, respuesta.getStatusCode()); 
        assertEquals("Pedido Eliminado", respuesta.getBody()); 
        verify(pedidoService, times(1)).eliminarPedido(1L);
    }
}
