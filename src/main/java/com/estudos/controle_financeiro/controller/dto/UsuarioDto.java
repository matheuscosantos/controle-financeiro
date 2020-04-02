package com.estudos.controle_financeiro.controller.dto;

import org.springframework.data.domain.Page;

import com.estudos.controle_financeiro.model.Usuario;

import lombok.Getter;

@Getter
public class UsuarioDto {
	
	private Long id;
	
	private String nome;
	
	private String email;
		
	public UsuarioDto(Usuario usuario) {
		
		this.id = usuario.getId();
		
		this.nome = usuario.getNome();
		
		this.email = usuario.getEmail();	
		
	}

	public static Page<UsuarioDto> converter(Page<Usuario> usuarios) {
		
		return usuarios.map(UsuarioDto::new);
		
	}

}
