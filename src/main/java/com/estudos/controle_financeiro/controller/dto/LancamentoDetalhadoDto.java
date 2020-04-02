package com.estudos.controle_financeiro.controller.dto;

import java.math.BigDecimal;

import com.estudos.controle_financeiro.model.Lancamento;
import com.estudos.controle_financeiro.model.Tipo;

import lombok.Getter;


@Getter
public class LancamentoDetalhadoDto {
	private Long id;	
	private String titulo;	
	private String descricao;	
	private Tipo tipo;	
	private String usuario;	
	private String email;
	private int dia;	
	private int mes;	
	private int ano;
	private BigDecimal valor;
	
	public LancamentoDetalhadoDto(Lancamento lancamento) {
		
		this.id = lancamento.getId();
		
		this.titulo = lancamento.getTitulo();
		
		this.descricao = lancamento.getDescricao();
		
		this.tipo = lancamento.getTipo();
		
		this.usuario = lancamento.getUsuario().getNome();
		
		this.email = lancamento.getUsuario().getEmail();
		
		this.dia = lancamento.getDia();
		
		this.mes = lancamento.getMes();
		
		this.ano = lancamento.getAno();
		
		this.valor = lancamento.getValor();
	}
}
