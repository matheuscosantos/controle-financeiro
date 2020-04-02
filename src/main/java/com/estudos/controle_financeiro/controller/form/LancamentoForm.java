package com.estudos.controle_financeiro.controller.form;

import java.math.BigDecimal;

import com.estudos.controle_financeiro.model.Lancamento;
import com.estudos.controle_financeiro.model.Tipo;
import com.estudos.controle_financeiro.model.Usuario;
import com.estudos.controle_financeiro.repository.UsuarioRepository;

import lombok.Setter;

@Setter
public class LancamentoForm {
	
	private String titulo;	
	
	private String descricao;	
	
	private Tipo tipo;	
	
	private String emailUsuario;
	
	private int dia;
	
	private int mes;	

	private int ano;
	
	private BigDecimal valor;

	public Lancamento converter(UsuarioRepository usuarioRepository) {
		
		Usuario usuario = usuarioRepository.findByEmail(emailUsuario);
		
		return new Lancamento(titulo, descricao, tipo, dia, mes, ano, valor, usuario);
	}
	
}
