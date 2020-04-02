package com.estudos.controle_financeiro.service;

import java.math.BigDecimal;

public interface RelatorioService {
	
	public BigDecimal calculaBalanco(Long idUsuario);
	
}
