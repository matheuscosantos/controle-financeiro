package com.estudos.controle_financeiro.controller.form;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginForm {
	
	private String email;
	
	private String senha;
	
	public UsernamePasswordAuthenticationToken converter() {
		
		return new UsernamePasswordAuthenticationToken(email, senha);
		
	}
	
}
