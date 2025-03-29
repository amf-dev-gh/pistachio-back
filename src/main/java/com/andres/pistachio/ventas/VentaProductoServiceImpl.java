package com.andres.pistachio.ventas;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andres.pistachio.producto.Producto;
import com.andres.pistachio.producto.ProductoRepository;

@Service
public class VentaProductoServiceImpl implements VentaProductoService {

	@Autowired
	VentaProductoRepository ventaProdRepo;
	
	@Autowired
	ProductoRepository prodRepo;

	@Override
	public List<VentaProducto> obtenerVentasPorProducto(Integer idProducto) {
		Producto producto = prodRepo.findById(idProducto)
				.orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
		return ventaProdRepo.findByProducto(producto);
	}

	@Override
	public List<VentaProducto> obtenerVentasPorProductoYFecha(ConsultaVentaDto consulta) {
		Producto producto = prodRepo.findById(consulta.getIdProducto())
				.orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
		
		return ventaProdRepo.findByProductoAndFechaVentaBetween(producto, consulta.getFechaInicio(), consulta.getFechaFin());
	}

	@Override
	public List<VentaProducto> obtenerVentas() {
		return ventaProdRepo.findAll();
	}

	@Override
	public List<VentaResponseDto> obtenerRanking() {
	    List<VentaProducto> ventas = ventaProdRepo.findAll();

	    return ventas.stream()
	            .collect(Collectors.groupingBy(VentaProducto::getProducto, 
	                   Collectors.summingInt(VentaProducto::getCantidadVendida)))
	            .entrySet()
	            .stream()
	            .map(entry -> 
	                new VentaResponseDto(entry.getKey().getNombre() + " x " + entry.getKey().getCantidad(), entry.getValue()))
	            .sorted(this::compararVentaResponseDto)
	            .toList();
	}

	// Método de ordenación
	private int compararVentaResponseDto(VentaResponseDto a, VentaResponseDto b) {
	    // Compara por cantidad desc
	    int cantidadComparison = Integer.compare(b.getCantidad(), a.getCantidad());

	    // Si las cantidades son iguales compara por nombre asc
	    if (cantidadComparison == 0) {
	        return a.getNombreProducto().compareTo(b.getNombreProducto());
	    }
	    return cantidadComparison;
	}


}
