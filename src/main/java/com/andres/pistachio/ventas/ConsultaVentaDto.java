package com.andres.pistachio.ventas;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ConsultaVentaDto {
	
	private LocalDate fechaInicio;
	
	private LocalDate fechaFin;
	
	private Integer idProducto;

}
