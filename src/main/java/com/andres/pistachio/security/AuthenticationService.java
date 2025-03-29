package com.andres.pistachio.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.andres.pistachio.login.CredencialesLoginDto;
import com.andres.pistachio.usuario.Rol;
import com.andres.pistachio.usuario.Usuario;
import com.andres.pistachio.usuario.UsuarioRepository;

@Service
public class AuthenticationService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private AuthenticationManager authenticationManager;

	/*
	 * Método que crea usuarios con rol de ADMIN. Solo utilizado al iniciar la APP.
	 */
	public Usuario signup(RegistroUsuarioDto input) {
		Usuario usuario = new Usuario(null, input.getNombre(), input.getApellido(), input.getEmail(),
				passwordEncoder.encode(input.getPassword()), input.getTelefono(), Rol.ADMIN, null);
		return usuarioRepository.save(usuario);
	}

	public Usuario authenticate(CredencialesLoginDto input) {
		authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(input.getEmail(), input.getPassword()));

		return usuarioRepository.findByEmail(input.getEmail()).orElseThrow();
	}
}