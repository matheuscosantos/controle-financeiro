package com.estudos.controle_financeiro.controller.dto;

import java.math.BigDecimal;

import org.springframework.data.domain.Page;

import com.estudos.controle_financeiro.model.Lancamento;
import com.estudos.controle_financeiro.model.Tipo;

import lombok.Getter;

@Getter
public class LancamentoDto {

	private Long id;	
	
	private String titulo;	
	
	private String descricao;	
	
	private Tipo tipo;	
	
	private String usuario;	
	
	private int dia;	
	
	private int mes;
	
	private int ano;
	
	private BigDecimal valor;
	
	public LancamentoDto(Lancamento lancamento) {
		
		this.id = lancamento.getId();
		
		this.titulo = lancamento.getTitulo();
		
		this.descricao = lancamento.getDescricao();
		
		this.tipo = lancamento.getTipo();
		
		this.usuario = lancamento.getUsuario().getNome();
		
		this.dia = lancamento.getDia();
		
		this.mes = lancamento.getMes();
		
		this.ano = lancamento.getAno();
		
		this.valor = lancamento.getValor();
	}

	public static Page<LancamentoDto> converter(Page<Lancamento> lancamentos) {
		
		return lancamentos.map(LancamentoDto::new);
		
	}
	
}
