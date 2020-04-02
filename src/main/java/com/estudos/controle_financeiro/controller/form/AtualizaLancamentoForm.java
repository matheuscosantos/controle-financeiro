package com.estudos.controle_financeiro.controller.form;

import java.math.BigDecimal;

import com.estudos.controle_financeiro.model.Lancamento;
import com.estudos.controle_financeiro.model.Tipo;
import com.estudos.controle_financeiro.repository.LancamentoRepository;

import lombok.Setter;

@Setter
public class AtualizaLancamentoForm {
	
	private String titulo;	
	
	private String descricao;	
	
	private Tipo tipo;	
		
	private int dia;
	
	private int mes;
	

	private int ano;
	
	private BigDecimal valor;

	public Lancamento atualiza(Long id, LancamentoRepository lancamentoRepository) {
		
		Lancamento lancamento = lancamentoRepository.getOne(id);
		
		lancamento.setTitulo(this.titulo);
		
		lancamento.setDescricao(this.descricao);
		
		lancamento.setTipo(this.tipo);
		
		lancamento.setDia(this.dia);
		
		lancamento.setMes(this.mes);
		
		lancamento.setAno(this.ano);
		
		lancamento.setValor(this.valor);
		
		return lancamento;
	}
	
}
