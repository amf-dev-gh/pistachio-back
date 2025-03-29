package com.andres.pistachio.categoria;

import java.util.List;
import java.util.Optional;

public interface CategoriaService {

	/*
	 * Obtiene listado de categorias de la BBDD
	 */
	List<Categoria> listarCategorias();

	/*
	 * Busca una categoria por su ID y retorna un OPTIONAL
	 */
	Optional<Categoria> buscarPorId(Integer categoriaId);

}
