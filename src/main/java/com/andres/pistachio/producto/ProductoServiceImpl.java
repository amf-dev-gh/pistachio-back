package com.andres.pistachio.producto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductoServiceImpl implements ProductoService {

	@Autowired
	ProductoRepository repo;

	@Override
	public List<Producto> listarProductos() {
		return repo.findAll();
	}
	
	@Override
	public List<Producto> buscarPorNombre(String nombreProducto) {
		return repo.findByNombreContainingIgnoreCase(nombreProducto);
	}

	@Override
	public List<Producto> buscarPorCategoria(Integer categoriaId) {
		return repo.findByCategoriaId(categoriaId);
	}

	@Override
	public Producto crearOActualizarProducto(Producto producto) {
		if(producto.getUrlImagen().equals("")) {
			producto.setUrlImagen("producto-img-default.webp");
		}
		return repo.save(producto);
	}

	@Override
	public boolean eliminarProducto(Integer id) {
	    if (repo.existsById(id)) {
	        repo.deleteById(id);
	        return true;
	    }
	    return false;
	}

}
