package com.andres.pistachio.ventas;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.andres.pistachio.producto.Producto;

public interface VentaProductoRepository extends JpaRepository<VentaProducto, Integer> {
	
	List<VentaProducto> findByProductoAndFechaVentaBetween(Producto producto, LocalDate startDate, LocalDate endDate);
	
	List<VentaProducto> findByProducto(Producto producto);

}
