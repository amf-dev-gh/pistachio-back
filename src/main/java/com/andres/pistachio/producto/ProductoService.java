package com.andres.pistachio.producto;

import java.util.List;

public interface ProductoService {

	/*
	 * Obtiene listado de productos disponibles en la BBDD
	 */
	public List<Producto> listarProductos();

	/*
	 * Obtiene un listado de productos filtrados por su nombre
	 */
	public List<Producto> buscarPorNombre(String nombreProducto);

	/*
	 * Obtiene un listado de productos diltrados por su categoría
	 */
	public List<Producto> buscarPorCategoria(Integer categoriaId);

	/*
	 * Crea o actualiza un producto, dependiendo de si esxiste o no en la BBDD
	 */
	public Producto crearOActualizarProducto(Producto producto);

	/*
	 * Elimina el producto cuyo id sea el mismo del parámetro
	 */
	public boolean eliminarProducto(Integer id);

}
