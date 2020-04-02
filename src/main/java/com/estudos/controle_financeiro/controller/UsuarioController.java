package com.estudos.controle_financeiro.controller;

import java.net.URI;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.estudos.controle_financeiro.controller.dto.UsuarioDto;
import com.estudos.controle_financeiro.controller.form.AtualizaUsuarioForm;
import com.estudos.controle_financeiro.controller.form.UsuarioForm;
import com.estudos.controle_financeiro.model.Usuario;
import com.estudos.controle_financeiro.repository.UsuarioRepository;

@RestController
@RequestMapping("/usuario")
public class UsuarioController  {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@GetMapping("/lista")
	@Cacheable(value = "listaDeUsuarios")
	public Page<UsuarioDto> listar(@PageableDefault(sort = "id", direction = Direction.ASC) Pageable paginacao){
		
		Page<Usuario> usuarios = usuarioRepository.findAll(paginacao);
		
		return UsuarioDto.converter(usuarios);
		
	}
	
	
//	@PostMapping
//	@Transactional
//	@CacheEvict(value = "listaDeUsuarios", allEntries = true)
//	public ResponseEntity<UsuarioDto> cadastrar(@RequestBody @Valid UsuarioForm usuarioForm, UriComponentsBuilder uriBuilder){
//		
//		Usuario usuario = usuarioForm.converter(usuarioForm);	
//		
//		usuarioRepository.save(usuario);
//		
//		URI uri = uriBuilder.path("/usuario/{id}").buildAndExpand(usuario.getId()).toUri();
//		
//		return ResponseEntity.created(uri).body(new UsuarioDto(usuario));
//		
//	}
	
	
	@GetMapping("/{id}")
	@Cacheable(value = "listaDeUsuarios")
	public ResponseEntity<UsuarioDto> detalhar(@PathVariable Long id){
		
		Optional<Usuario> usuario = usuarioRepository.findById(id);
		
		if(usuario.isPresent()) {
			
			return ResponseEntity.ok(new UsuarioDto(usuario.get()));
			
		}
		
		return ResponseEntity.notFound().build();
	}
	
	
	@DeleteMapping("/{id}")
	@Transactional
	@CacheEvict(value = "listaDeUsuarios", allEntries = true)
	public ResponseEntity<?> remover(@PathVariable Long id){
		
		Optional<Usuario> optional = usuarioRepository.findById(id);
		
		if(optional.isPresent()) {
			
			usuarioRepository.deleteById(id);
			
			return ResponseEntity.ok().build();
			
		}
		
		return ResponseEntity.notFound().build();
	}
	
	
	@PutMapping("/{id}")
	@Transactional
	@CacheEvict(value = "listaDeUsuarios", allEntries = true)
	public ResponseEntity<UsuarioDto> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizaUsuarioForm form){
		
		Optional<Usuario> optional = usuarioRepository.findById(id);
		
		if(optional.isPresent()) {
			
			Usuario usuario = form.atualizar(id, usuarioRepository);
			
			return ResponseEntity.ok(new UsuarioDto(usuario));
			
		}
		
		return ResponseEntity.notFound().build();
	}
	
}

