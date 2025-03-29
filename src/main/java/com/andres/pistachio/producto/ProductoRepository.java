package com.andres.pistachio.producto;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer>{
	
	List<Producto> findByCategoriaId(Integer categoriaId);
	
	List<Producto> findByNombreContainingIgnoreCase(String nombre);
	

}
