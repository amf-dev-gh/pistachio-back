package com.andres.pistachio.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.andres.pistachio.security.AuthenticationService;
import com.andres.pistachio.security.JwtService;
import com.andres.pistachio.security.RegistroUsuarioDto;
import com.andres.pistachio.usuario.Usuario;
import com.andres.pistachio.usuario.UsuarioService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(value = "http://localhost:4200")
public class LoginController {

	@Autowired
	private JwtService jwtService;

	@Autowired
	private AuthenticationService authenticationService;

	@Autowired
	private UsuarioService usuarioService;

	/*
	 * Recibe credenciales y retorna un loginResponse con las informacion obtenida
	 * en caso correcto.
	 */
	@PostMapping("/login")
	public ResponseEntity<LoginResponseDto> iniciarSesion(@RequestBody CredencialesLoginDto credenciales) {
		Usuario usuarioAutentificado = authenticationService.authenticate(credenciales);
		String jwToken = jwtService.generateToken(usuarioAutentificado);
		LoginResponseDto loginResponse = new LoginResponseDto(credenciales.getEmail(),
				usuarioAutentificado.getRol().toString(), jwToken, jwtService.getExpirationTime());
		return ResponseEntity.ok(loginResponse);
	}

	/*
	 * Método no consumido por el momento.
	 * 
	 * Registro de un nuevo usuario como CLIENTE en la BBDD.
	 */
	@PostMapping("/registro")
	public ResponseEntity<Usuario> registrarNuevoUsuario(@RequestBody RegistroUsuarioDto registroDto) {
		Usuario nuevoUsuario = usuarioService.crearUsuarioCliente(registroDto);
		return ResponseEntity.ok(nuevoUsuario);
	}

}
