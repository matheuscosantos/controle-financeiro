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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.estudos.controle_financeiro.controller.dto.LancamentoDetalhadoDto;
import com.estudos.controle_financeiro.controller.dto.LancamentoDto;
import com.estudos.controle_financeiro.controller.form.AtualizaLancamentoForm;
import com.estudos.controle_financeiro.controller.form.LancamentoForm;
import com.estudos.controle_financeiro.model.Lancamento;
import com.estudos.controle_financeiro.repository.LancamentoRepository;
import com.estudos.controle_financeiro.repository.UsuarioRepository;

@RestController
@RequestMapping("/lancamento")
public class LancamentoController {
	
	@Autowired
	private LancamentoRepository lancamentoRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@PostMapping
	@Transactional
	@CacheEvict(value = "listaDeLancamentos", allEntries = true)
	public ResponseEntity<LancamentoDto> cadastrar(@RequestBody @Valid LancamentoForm lancamentoForm, UriComponentsBuilder uriBuilder){
		
		Lancamento lancamento = lancamentoForm.converter(usuarioRepository);		
		
		lancamentoRepository.save(lancamento);		
		
		URI uri = uriBuilder.path("/lancamento/{id}").buildAndExpand(lancamento.getId()).toUri();
		
		return ResponseEntity.created(uri).body(new LancamentoDto(lancamento));
	}
	
	
	@PutMapping("/{id}")
	@Transactional
	@CacheEvict(value = "listaDeLancamentos", allEntries = true)
	public ResponseEntity<LancamentoDto> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizaLancamentoForm form){
		
		Optional<Lancamento> optional = lancamentoRepository.findById(id);
		
		if(optional.isPresent()) {
			
			Lancamento lancamento = form.atualiza(id, lancamentoRepository);
			
			return ResponseEntity.ok(new LancamentoDto(lancamento));
		}
		
		return ResponseEntity.notFound().build();
	}	
	
	
	@GetMapping
	@Cacheable(value="listaDeLancamentos")
	public Page<LancamentoDto> lista(@RequestParam(required = false) String email,
									 @PageableDefault(sort = "id", direction = Direction.ASC) Pageable paginacao){
		
		if(email == null) {
			
			Page<Lancamento> lancamentos = lancamentoRepository.findAll(paginacao);
			
			return LancamentoDto.converter(lancamentos);
			
		}else {
			
			Page<Lancamento> lancamentos = lancamentoRepository.findByUsuarioEmail(email, paginacao);
			
			return LancamentoDto.converter(lancamentos);
		}
	}

	
	@GetMapping("/{id}")
	public ResponseEntity<LancamentoDetalhadoDto> detalhar(@PathVariable Long id){
		
		Optional<Lancamento> lancamento = lancamentoRepository.findById(id);
		
		if(lancamento.isPresent()) {
			
			return ResponseEntity.ok(new LancamentoDetalhadoDto(lancamento.get()));
			
		}
		
		return ResponseEntity.notFound().build();
	}
	
	
	@DeleteMapping("/{id}")
	@Transactional
	
	@CacheEvict(value = "listaDeLancamentos", allEntries = true)
	public ResponseEntity<?> remover(@PathVariable Long id){
		
		Optional<Lancamento> optional = lancamentoRepository.findById(id);
		
		if(optional.isPresent()) {
			
			lancamentoRepository.deleteById(id);
			
			return ResponseEntity.ok().build();
		}
		
		return ResponseEntity.notFound().build();
		
	}
	
	
}
