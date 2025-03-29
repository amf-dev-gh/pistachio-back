package com.andres.pistachio.pedido;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

	@Autowired
	private PedidoService pedidoService;

	/*
	 * Obtiene listado de pedidos de la BBDD. Caso que no existan, retorna NO
	 * CONTENT
	 */
	@GetMapping("/listar")
	public ResponseEntity<List<Pedido>> listarPedidos() {
		List<Pedido> pedidos = pedidoService.listarPedidos();
		if (pedidos.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(pedidos);
	}

	/*
	 * Crea pedido en la BBDD y luego retorna un PedidoResponseDTO con datos del
	 * pedido creado.
	 */
	@PostMapping
	public ResponseEntity<PedidoResponseDto> crearPedido(@RequestBody PedidoDto pedidoDTO) {
		Pedido pedidoCreado = pedidoService.crearPedido(pedidoDTO);
		PedidoResponseDto response = new PedidoResponseDto(pedidoCreado.getId(), pedidoCreado.getUsuario().getId(),
				pedidoCreado.getNumeroPedido(), pedidoCreado.getEstado().toString());
		return ResponseEntity.ok(response);
	}

	/*
	 * Cambia el estado de un pedido. Si el estado es CANCELADO, cancela el pedido
	 * en la BBDD. Si el estado es diferente de cancelado lo cambia entre PENDIENTE
	 * Y ENTREGADO.
	 * 
	 * Caso de error al actualizar el estado retorna un NOT FOUND
	 * 
	 * Caso de recibir un estado distinto de CANCELADO, ENTREGADO o PENDIENTE,
	 * retora Internal Server ERROR.
	 */
	@PutMapping("/{id}/estado")
	public ResponseEntity<?> actualizarEstadoPedido(@PathVariable Integer id, @RequestBody String nuevoEstado) {
		try {
			EstadoPedido estadoEnum = EstadoPedido.valueOf(nuevoEstado.toUpperCase());

			Pedido pedidoActualizado;
			if (estadoEnum == EstadoPedido.CANCELADO) {
				pedidoActualizado = pedidoService.cancelarPedido(id);
			} else {
				pedidoActualizado = pedidoService.actualizarEstado(id, estadoEnum);
			}

			if (pedidoActualizado == null) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se pudo actualizar el pedido.");
			}

			PedidoResponseDto response = new PedidoResponseDto(id,
					pedidoActualizado.getUsuario() != null ? pedidoActualizado.getUsuario().getId() : null,
					pedidoActualizado.getNumeroPedido(), pedidoActualizado.getEstado().toString());
			return ResponseEntity.ok(response);

		} catch (IllegalArgumentException e) {
			return ResponseEntity.badRequest().body("Estado no válido: " + nuevoEstado);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar el pedido.");
		}
	}
}