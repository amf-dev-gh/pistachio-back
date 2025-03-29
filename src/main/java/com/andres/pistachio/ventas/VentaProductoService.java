package com.andres.pistachio.ventas;

import java.util.List;

public interface VentaProductoService {

	/*
	 * Obtiene la cantidad total vendidas de un producto recibido por parametro.
	 */
	public List<VentaProducto> obtenerVentasPorProducto(Integer idProducto);

	/*
	 * Obtiene la una lista de productos vendidos entre una fecha determinada.
	 */
	public List<VentaProducto> obtenerVentasPorProductoYFecha(ConsultaVentaDto consulta);
	
	/*
	 * Obtiene todas las ventas registradas.
	 */
	public List<VentaProducto> obtenerVentas();
	
	/*
	 * Obtiene el ranking de ventas por producto ordenada de mas vendido a menos vendido
	 */
	public List<VentaResponseDto> obtenerRanking();
	

}
