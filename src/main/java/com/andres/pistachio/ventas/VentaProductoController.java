package com.andres.pistachio.ventas;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/ventas")
public class VentaProductoController {

	@Autowired
	VentaProductoService ventaProdService;

	/*
	 * Retorna un listado de todas las ventas registradas.
	 * 
	 * Caso de no haber ventas retorna NO CONTENT
	 */
	@GetMapping("/")
	public ResponseEntity<List<VentaProducto>> listarVentas() {
		List<VentaProducto> ventas = ventaProdService.obtenerVentas();
		if (ventas.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(ventas);
	}

	/*
	 * Retorna todas las ventas de un producto especifico
	 * 
	 * Caso de no haber ventas retorna NO CONTENT
	 */
	@GetMapping("/producto/{idProducto}")
	public ResponseEntity<List<VentaProducto>> listarVentasPorProducto(@PathVariable Integer idProducto) {
		List<VentaProducto> ventas = ventaProdService.obtenerVentasPorProducto(idProducto);
		if (ventas.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(ventas);
	}

	/*
	 * Retorna un listado de ventas de un producto comprendido entre dos fechas
	 * @param consulta
	 * 
	 * Caso de no haber ventas retorna NO CONTENT
	 */
	@PostMapping("/producto/fecha")
	public ResponseEntity<List<VentaProducto>> listarVentasPorProductoYFecha(@RequestBody ConsultaVentaDto consulta) {
		System.out.println(consulta);
		List<VentaProducto> ventas = ventaProdService.obtenerVentasPorProductoYFecha(consulta);
		if (ventas.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(ventas);
	}
	
	/*
	 * Retorna un listado con los productos vendidos ordenados de mayor a menor por su cantidad
	 * 
	 * Caso de no existir ventas, retorna NO CONTENT
	 */
    @GetMapping("/ranking")
    public ResponseEntity<List<VentaResponseDto>> obtenerRankingVentas() {
        List<VentaResponseDto> ranking = ventaProdService.obtenerRanking();
        if (ranking.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(ranking);
    }
}
