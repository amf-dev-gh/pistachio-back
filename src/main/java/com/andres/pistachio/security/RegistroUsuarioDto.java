package com.andres.pistachio.security;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegistroUsuarioDto {

	private String email;

	private String password;

	private String nombre;

	private String apellido;
	
	private String telefono;

}
