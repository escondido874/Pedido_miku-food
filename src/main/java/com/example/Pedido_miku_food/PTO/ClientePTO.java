package com.example.Pedido_miku_food.PTO;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientePTO {

    private Integer id;
    
    private String nombre;

    private String apellido_paterno;

    private String apellido_materno;

    private String correo;

    private Date fechaNacimiento;

    private Integer telefono;

}
