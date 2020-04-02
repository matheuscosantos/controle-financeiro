package com.estudos.controle_financeiro.service.impl;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estudos.controle_financeiro.model.Usuario;
import com.estudos.controle_financeiro.repository.LancamentoRepository;
import com.estudos.controle_financeiro.repository.UsuarioRepository;
import com.estudos.controle_financeiro.service.RelatorioService;

@Service
public class RelatorioServiceImpl implements RelatorioService{
	
	@Autowired
	private LancamentoRepository lancamentoRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public BigDecimal calculaBalanco(Long idUsuario) {
		
		Optional<Usuario> usuario = usuarioRepository.findById(idUsuario);
				
		if(usuario.isPresent()) {
			
			BigDecimal totalEntradas = lancamentoRepository.getValorTotalEntradas(idUsuario);
			
			if(totalEntradas == null) {
				
				totalEntradas = BigDecimal.ZERO;
				
			}
			
			BigDecimal totalSaidas = lancamentoRepository.getValorTotalSaidas(idUsuario);
			
			if(totalSaidas == null) {
				
				totalSaidas = BigDecimal.ZERO;
				
			}
			
			BigDecimal resultado = totalEntradas.subtract(totalSaidas);
			
			return resultado;
			
		}
		
		return null;		
	}
}
