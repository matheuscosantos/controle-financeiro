package com.estudos.controle_financeiro.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario implements UserDetails{

	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nome;
	
	@Column(unique=true)
	private String email;
	
	private String senha;
	
	@ManyToMany(fetch = FetchType.EAGER)
	private List<Perfil> perfis = new ArrayList<>();
	
	public Usuario(String nome, String email, String senha) {
		
		super();
		
		this.nome = nome;
		
		this.email = email;
		
		this.senha = senha;
		
	}	
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		return this.perfis;
		
	}
	
	@Override
	public String getPassword() {
		
		return this.senha;
		
	}
	
	@Override
	public String getUsername() {
		
		return this.email;
		
	}
	
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	
	@Override
	public boolean isAccountNonLocked() {
		
		return true;
		
	}
	
	@Override
	public boolean isCredentialsNonExpired() {
		
		return true;
		
	}
	
	@Override
	public boolean isEnabled() {
		
		return true;
		
	}

}
