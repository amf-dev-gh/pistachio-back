package com.andres.pistachio.producto;

import com.andres.pistachio.categoria.Categoria;

import jakarta.persistence.Column;
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
import lombok.ToString;

@Entity
@Table(name = "productos")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Producto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nombre;
	@ManyToOne
	@JoinColumn(name = "categoria_id", nullable = false)
	private Categoria categoria;
	private String descripcion;
	private String cantidad;
	private Integer precio;
	@Column(name = "url_imagen")
	private String urlImagen;
	private boolean destacado;
	private Integer stock;
}
