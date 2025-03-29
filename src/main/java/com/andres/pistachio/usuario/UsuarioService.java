package com.andres.pistachio.usuario;

import com.andres.pistachio.security.RegistroUsuarioDto;

public interface UsuarioService {
	
	/*
	 * Registra un nuevo usuario de tipo CLIENTE en la BBDD
	 */
	Usuario crearUsuarioCliente(RegistroUsuarioDto usuarioDto);

}
