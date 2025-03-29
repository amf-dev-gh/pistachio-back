package com.andres.pistachio.itempedido;

import com.andres.pistachio.pedido.Pedido;
import com.andres.pistachio.producto.Producto;
import com.fasterxml.jackson.annotation.JsonBackReference;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "items")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemPedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "producto_id", nullable = false)
	private Producto producto;

	@NonNull
	private int cantidad;

	@NonNull
	private int subtotal;

    @ManyToOne
    @JoinColumn(name = "pedido_id", nullable = false)
    @JsonBackReference
    private Pedido pedido;

}
