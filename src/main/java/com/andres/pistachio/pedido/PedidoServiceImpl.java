package com.andres.pistachio.pedido;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andres.pistachio.itempedido.ItemPedido;
import com.andres.pistachio.itempedido.ItemPedidoDto;
import com.andres.pistachio.producto.Producto;
import com.andres.pistachio.producto.ProductoRepository;
import com.andres.pistachio.usuario.Rol;
import com.andres.pistachio.usuario.Usuario;
import com.andres.pistachio.usuario.UsuarioRepository;
import com.andres.pistachio.ventas.VentaProducto;
import com.andres.pistachio.ventas.VentaProductoRepository;

import jakarta.transaction.Transactional;

@Service
public class PedidoServiceImpl implements PedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private ProductoRepository productoRepository;
	
	@Autowired
	private VentaProductoRepository ventaProdRepo;

	public Pedido crearPedido(PedidoDto pedidoDTO) {
		boolean usuarioExistente = usuarioRepository.existsByEmail(pedidoDTO.getUsuario().getEmail());
		Usuario usuario = new Usuario();
		if (usuarioExistente) {
			usuario = usuarioRepository.findByEmail(pedidoDTO.getUsuario().getEmail())
					.orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
		} else {
			usuario.setNombre(pedidoDTO.getUsuario().getNombre());
			usuario.setApellido(pedidoDTO.getUsuario().getApellido());
			usuario.setEmail(pedidoDTO.getUsuario().getEmail());
			usuario.setTelefono(pedidoDTO.getUsuario().getTelefono());
			usuario.setRol(Rol.CLIENTE);
			usuarioRepository.save(usuario);
		}

		Pedido pedido = new Pedido();
		pedido.setUsuario(usuario);
		pedido.setEstado(EstadoPedido.PENDIENTE);

		List<ItemPedido> items = new ArrayList<>();
		double total = 0.0;

		for (ItemPedidoDto itemDTO : pedidoDTO.getItems()) {
			Producto producto = productoRepository.findById(itemDTO.getProductoId())
					.orElseThrow(() -> new RuntimeException("Producto no encontrado"));
			
	        // Verificar si hay suficiente stock
	        // Actualizar el stock
	        actualizarStock(producto, itemDTO);

			ItemPedido itemPedido = new ItemPedido();
			itemPedido.setId(null);
			itemPedido.setProducto(producto);
			itemPedido.setCantidad(itemDTO.getCantidad());
			itemPedido.setSubtotal(producto.getPrecio() * itemDTO.getCantidad());
			itemPedido.setPedido(pedido);

			total += itemPedido.getSubtotal();
			items.add(itemPedido);
		}

		pedido.setItems(items);
		pedido.setTotal(total);
		pedido.setNumeroPedido(crearNumeroPedido());
		pedido.setFecha(LocalDate.now());
		
		return pedidoRepository.save(pedido);
	}

	@Override
	public Pedido actualizarEstado(Integer id, EstadoPedido nuevoEstado) {
		Pedido pedido = pedidoRepository.findById(id).orElseThrow(() -> new RuntimeException("Pedido no encontrado"));

		pedido.setEstado(nuevoEstado);
		return pedidoRepository.save(pedido);
	}
	
	@Override
	@Transactional
	public Pedido cancelarPedido(Integer id) {
	    Pedido pedido = pedidoRepository.findById(id)
	            .orElseThrow(() -> new RuntimeException("Pedido no encontrado"));
	    
	    pedido.setEstado(EstadoPedido.CANCELADO);

	    for (ItemPedido item : pedido.getItems()) {
	        Producto producto = productoRepository.findById(item.getProducto().getId())
	                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

	        producto.setStock(producto.getStock() + item.getCantidad());
	        productoRepository.save(producto);
	    }

	    return pedidoRepository.save(pedido);
	}

	@Override
	public List<Pedido> listarPedidos() {
		return pedidoRepository.findAll();
	}
	
	private String crearNumeroPedido() {
		long totalPedidos = pedidoRepository.count();
		String numeroPedido = String.format("001-%04d", totalPedidos + 1);
		return numeroPedido;
	}
	
	private void actualizarStock(Producto producto, ItemPedidoDto itemDTO) {
        if (producto.getStock() < itemDTO.getCantidad()) {
            throw new RuntimeException("No hay suficiente stock para el producto: " + producto.getNombre());
        }
        producto.setStock(producto.getStock() - itemDTO.getCantidad());
        //Tambien crea unna nueva venta
        ventaProdRepo.save(new VentaProducto(null,producto,itemDTO.getCantidad(), LocalDate.now()));
        productoRepository.save(producto);
	}

}
