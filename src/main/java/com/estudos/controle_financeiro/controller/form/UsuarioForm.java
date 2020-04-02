package com.estudos.controle_financeiro.controller.form;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.estudos.controle_financeiro.model.Usuario;
import com.sun.istack.NotNull;

import lombok.Getter;

@Getter
public class UsuarioForm {
	
	@NotNull @NotEmpty @Length(min = 10, max = 50)
	private String nome;
	
	@NotNull @NotEmpty @Email @Column(unique=true)
	private String email;
	
	@NotEmpty @NotEmpty @Length(min = 8, max = 20)
	private String senha;

	public UsuarioForm(String nome, String email, String senha) {
		
		this.nome = nome;
		
		this.email = email;
		
		this.senha = senha;
	}

	public Usuario converter(UsuarioForm usuarioForm) {
		
		Usuario usuario = new Usuario(usuarioForm.getNome(),usuarioForm.getEmail(), usuarioForm.getSenha());
		
		return usuario;

	}

}
