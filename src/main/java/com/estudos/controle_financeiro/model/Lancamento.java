package com.estudos.controle_financeiro.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Lancamento {	
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String titulo;
	
	private String descricao;
	
	@Enumerated(EnumType.STRING)
	private Tipo tipo;
	
	@ManyToOne
	private Usuario usuario;
	
	private int dia;
	
	private int mes;
	
	private int ano;
	
	private BigDecimal valor;
	
	public Lancamento(String titulo, String descricao, Tipo tipo, int dia, int mes, int ano, BigDecimal valor,	Usuario usuario) {
		
		this.titulo = titulo;
		
		this.descricao = descricao;
		
		this.tipo = tipo;
	
		this.dia = dia;
	
		this.mes = mes;
		
		this.ano = ano;
		
		this.valor = valor;
		
		this.usuario = usuario;
	}
	
}
