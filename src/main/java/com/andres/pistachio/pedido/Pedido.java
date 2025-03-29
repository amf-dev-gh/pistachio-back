package com.andres.pistachio.pedido;

import java.time.LocalDate;
import java.util.List;

import com.andres.pistachio.itempedido.ItemPedido;
import com.andres.pistachio.usuario.Usuario;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "pedidos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String numeroPedido;
	
	@Column(name = "fecha_pedido", nullable = false)
	private LocalDate fecha;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    @JsonManagedReference
    private Usuario usuario;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<ItemPedido> items;

	private Double total;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private EstadoPedido estado;
}
