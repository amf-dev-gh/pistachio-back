package com.andres.pistachio.pedido;

import java.util.List;

public interface PedidoService {

	/*
	 * Crea pedido en la BBDD y retorna el pedido creado.
	 */
	public Pedido crearPedido(PedidoDto pedidoDTO);

	/*
	 * Actualiza el estado del pedido y retorna el pedido actualizado.
	 */
	public Pedido actualizarEstado(Integer id, EstadoPedido nuevoEstado);

	/*
	 * Cambia el estado del pedido a CANCELADO y retorna el pedido actualizado
	 */
	public Pedido cancelarPedido(Integer id);

	/*
	 * Obtiene listado de todos los pedidos de la BBDD
	 */
	public List<Pedido> listarPedidos();

}
