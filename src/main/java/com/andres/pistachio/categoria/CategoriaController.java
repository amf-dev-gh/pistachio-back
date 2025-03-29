package com.andres.pistachio.categoria;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/categorias")
@CrossOrigin(value = "http://localhost:4200")
public class CategoriaController {

	@Autowired
	CategoriaService catService;

	/*
	 * Retorna un listado de las categorias disponibles en la BBDD. Caso de lista
	 * vacia retorna NO CONTENT
	 */
	@GetMapping
	public ResponseEntity<List<Categoria>> listarCategorias() {
		List<Categoria> categorias = catService.listarCategorias();
		if (categorias.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(categorias);
	}

	/*
	 * Retorna la categoria obtenida que tenga como id el categoriaId pasado por
	 * parametro. Caso contrario retorna NOT FOUND
	 */
	@GetMapping("/{categoriaId}")
	public ResponseEntity<Categoria> buscarCategoriaPorId(@PathVariable Integer categoriaId) {
		Optional<Categoria> categoriaOpt = catService.buscarPorId(categoriaId);
		if (categoriaOpt.isPresent()) {
			return ResponseEntity.ok(categoriaOpt.get());
		}
		return ResponseEntity.notFound().build();
	}

}
