package com.andres.pistachio.pedido;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PedidoResponseDto {
	
	private Integer pedidoId;
	private Integer usuarioId;
	private String numeroPedido;
	private String estado;

}
