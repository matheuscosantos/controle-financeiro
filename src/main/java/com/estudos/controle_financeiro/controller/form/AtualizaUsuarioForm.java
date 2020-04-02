package com.estudos.controle_financeiro.controller.form;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.estudos.controle_financeiro.model.Usuario;
import com.estudos.controle_financeiro.repository.UsuarioRepository;

import lombok.Getter;

@Getter
public class AtualizaUsuarioForm {
	
	@NotNull @NotEmpty @Length(min = 10, max = 50)
	public String nome;
	
	@NotNull @NotEmpty @Email @Column(unique=true)
	public String email;
	
//	@NotEmpty @NotEmpty @Length(min = 8, max = 20)
//	public String senha;

	public Usuario atualizar(Long id, UsuarioRepository usuarioRepository) {
		
		Usuario usuario = usuarioRepository.getOne(id);
		
		usuario.setNome(this.nome);
		
		usuario.setEmail(this.email);
		
//		usuario.setSenha(this.senha);
		
		return usuario;
	}
	
	
}
