package com.estudos.controle_financeiro.repository;

import java.math.BigDecimal;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.estudos.controle_financeiro.model.Lancamento;
import com.estudos.controle_financeiro.model.Tipo;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long>{
	
	Page<Lancamento> findByUsuarioNome(String nomeUsuario, Pageable paginacao);

	Page<Lancamento> findByUsuarioEmail(String email, Pageable paginacao);

	Page<Lancamento> findByUsuarioIdAndTipo(Long id, Tipo tipo, Pageable paginacao);

	Page<Lancamento> findByUsuarioIdAndMesAndAno(Long id, int mes, int ano, Pageable paginacao);
	
	
	@Query(value = "select sum(l.valor) from Lancamento l join l.usuario u" 
			    + " where u.id = :id and l.tipo ='ENTRADA' group by u")		
	BigDecimal getValorTotalEntradas(@Param("id") Long idUsuario);

	
	@Query(value = "select sum(l.valor) from Lancamento l join l.usuario u" 
			    + " where u.id = :id and l.tipo ='SAIDA' group by u")	
	BigDecimal getValorTotalSaidas(@Param("id") Long idUsuario);
	
}
