package com.andres.pistachio.ventas;

import java.time.LocalDate;

import com.andres.pistachio.producto.Producto;
import com.fasterxml.jackson.annotation.JsonFormat;

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
@Table(name = "venta_por_producto")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class VentaProducto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "producto_id", nullable = false)
    private Producto producto;

    @Column(name = "cantidad_vendida", nullable = false)
    private Integer cantidadVendida;
    
    @Column(name = "fecha_venta", nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaVenta;
}