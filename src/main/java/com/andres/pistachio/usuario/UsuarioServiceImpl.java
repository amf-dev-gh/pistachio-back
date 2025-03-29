package com.andres.pistachio.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.andres.pistachio.security.RegistroUsuarioDto;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	UsuarioRepository usuarioRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public Usuario crearUsuarioCliente(RegistroUsuarioDto usuarioDto) {
		boolean existe = usuarioRepo.existsByEmail(usuarioDto.getEmail());
		if(existe) {
			System.out.println("Usuario existe en la BBDD");
			return usuarioRepo.findByEmail(usuarioDto.getEmail()).get();
		}
		Usuario nuevoUsuario = new Usuario(null, usuarioDto.getNombre(), usuarioDto.getApellido(),
				usuarioDto.getEmail(), passwordEncoder.encode(usuarioDto.getPassword()), usuarioDto.getTelefono(), Rol.CLIENTE, null);
		return usuarioRepo.save(nuevoUsuario);
	}

}
