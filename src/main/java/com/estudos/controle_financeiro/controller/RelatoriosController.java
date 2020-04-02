package com.estudos.controle_financeiro.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.estudos.controle_financeiro.controller.dto.LancamentoDto;
import com.estudos.controle_financeiro.model.Lancamento;
import com.estudos.controle_financeiro.model.Tipo;
import com.estudos.controle_financeiro.repository.LancamentoRepository;
import com.estudos.controle_financeiro.service.RelatorioService;

@RestController
@RequestMapping("/relatorio")
public class RelatoriosController {
	
	@Autowired
	private LancamentoRepository lancamentoRepository;
	
	@Autowired
	private RelatorioService relatorioService;
	
	@GetMapping("/listaportipo")
	public Page<LancamentoDto> listaEntradas( @RequestParam Long idUsuario, 
											  @RequestParam Tipo tipo, 
											  @PageableDefault(
												   sort = "id", direction = Direction.ASC) Pageable paginacao){
		
		Page<Lancamento> lancamentos = lancamentoRepository.findByUsuarioIdAndTipo(idUsuario, tipo, paginacao);
		
		return LancamentoDto.converter(lancamentos);
	}
	
	
	@GetMapping("/listapormeseano")
	public Page<LancamentoDto> listaEntradas( @RequestParam Long idUsuario, 
											  @RequestParam int mes, 
											  @RequestParam int ano,
											  @PageableDefault(
													  sort = "id", direction = Direction.ASC) Pageable paginacao){
		
		Page<Lancamento> lancamentos = lancamentoRepository.findByUsuarioIdAndMesAndAno(idUsuario, mes, ano, paginacao);	
		
		return LancamentoDto.converter(lancamentos);
	}
	
	
	@GetMapping("/somaentradas")
	public ResponseEntity<BigDecimal> retornaSomaEntradas(@RequestParam Long idUsuario) {
		
		BigDecimal balancoPorTipoEUsuario = lancamentoRepository.getValorTotalEntradas(idUsuario);
		
		return ResponseEntity.ok(balancoPorTipoEUsuario);
	}
	
	
	@GetMapping("/somasaidas")
	public ResponseEntity<BigDecimal> retornaSomaSaidas(@RequestParam Long idUsuario) {
		
		BigDecimal balancoPorTipoEUsuario = lancamentoRepository.getValorTotalSaidas(idUsuario);
		
		return ResponseEntity.ok(balancoPorTipoEUsuario);
	}
	
	
	@GetMapping("/balanco")
	public ResponseEntity<BigDecimal> balanco(@RequestParam Long idUsuario) {
		
		BigDecimal resultado = relatorioService.calculaBalanco(idUsuario);
		
		if(resultado != null) {
			return ResponseEntity.ok(resultado);
		}		
		
		return ResponseEntity.notFound().build();		
		
	}	
		
}
