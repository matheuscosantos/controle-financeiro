package com.estudos.controle_financeiro.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.estudos.controle_financeiro.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	
	Usuario findByEmail(String email);

}
