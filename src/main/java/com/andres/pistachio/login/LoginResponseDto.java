package com.andres.pistachio.login;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
 * Respuesta enviada al logearse. Contiene token, expiracion del mimso, email y rol.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponseDto {
	
	private String email;
	private String rol;
	private String token;
	private long expiracion;

}
