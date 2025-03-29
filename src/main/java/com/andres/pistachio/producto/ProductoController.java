package com.andres.pistachio.producto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

	@Autowired
	ProductoService prodService;

	/*
	 * Retorna una respuesta OK con el listado de todos los productos disponibles
	 * 
	 * Caso de no haber productos retorna NO CONTENT
	 */
	@GetMapping
	public ResponseEntity<List<Producto>> listarProductos() {
		List<Producto> productos = prodService.listarProductos();
		if (productos.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(productos);
	}

	/*
	 * Retorna una respuesta OK con el listado de todos los productos disponibles de
	 * la categoria especifica
	 * 
	 * Caso de no haber productos retorna NO CONTENT
	 */
	@GetMapping("/categoria/{categoriaId}")
	public ResponseEntity<List<Producto>> listarProductosPorCategoria(@PathVariable Integer categoriaId) {
		List<Producto> productos = prodService.buscarPorCategoria(categoriaId);
		if (productos.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(productos);
	}

	/*
	 * Retorna respuesta OK con un listado de productos filtrados por su nombre.
	 * 
	 * * Caso de no encontrar productos retorna NO CONTENT
	 */
	@GetMapping("/{nombreProducto}")
	public ResponseEntity<List<Producto>> obtenerProductosPorNombre(@PathVariable String nombreProducto) {
		List<Producto> productosFiltrados = prodService.buscarPorNombre(nombreProducto);
		if (productosFiltrados.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(productosFiltrados);
	}

	/*
	 * Obtiene un producto por parametro y lo actualiza o lo guarda caso de que no
	 * exista en la BBDD
	 * 
	 * Caso creado(nuevo) la respuesta es CREATED, caso actualizado respuesta OK
	 */
	@PostMapping("/guardar")
	public ResponseEntity<Producto> guardarOActualizarProducto(@RequestBody Producto producto) {
		boolean nuevo = (producto.getId() == null);
		Producto prodObtenido = prodService.crearOActualizarProducto(producto);
		if (nuevo) {
			return ResponseEntity.status(HttpStatus.CREATED).body(prodObtenido);
		} else {
			return ResponseEntity.ok(prodObtenido);
		}
	}

	/*
	 * Elimina el producto que tenga como ID, el recibido por parametro.
	 * 
	 * Caso de ser eliminado rotorna una respuesta OK sin contenido. Caso de error o
	 * no existencia retorna NOT FOUND
	 */
	@DeleteMapping("/eliminar/{idProducto}")
	public ResponseEntity<Void> eliminarProductoPorId(@PathVariable Integer idProducto) {
		return prodService.eliminarProducto(idProducto) ? ResponseEntity.noContent().build()
				: ResponseEntity.notFound().build();
	}

}
