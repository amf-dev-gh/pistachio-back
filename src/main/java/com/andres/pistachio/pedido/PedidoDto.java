package com.andres.pistachio.pedido;

import java.util.List;

import com.andres.pistachio.itempedido.ItemPedidoDto;
import com.andres.pistachio.usuario.UsuarioDto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PedidoDto {
	
	private UsuarioDto usuario;
    private List<ItemPedidoDto> items;

}
