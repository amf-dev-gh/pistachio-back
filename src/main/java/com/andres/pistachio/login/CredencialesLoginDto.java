package com.andres.pistachio.login;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
 * Credenciales recibidas para inicio de sesion
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CredencialesLoginDto {

	private String email;
	private String password;

}
