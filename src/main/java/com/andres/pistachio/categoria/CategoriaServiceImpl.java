package com.andres.pistachio.categoria;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoriaServiceImpl implements CategoriaService{
	
	@Autowired
	CategoriaRepository repo;

	@Override
	public List<Categoria> listarCategorias() {
		return repo.findAll();
	}

	@Override
	public Optional<Categoria> buscarPorId(Integer categoriaId) {
		return repo.findById(categoriaId);
	}

}
