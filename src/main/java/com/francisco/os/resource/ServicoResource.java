package com.francisco.os.resource;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.francisco.os.domain.Servico;
import com.francisco.os.dtos.ServicoDTO;
import com.francisco.os.services.ServicoService;

import jakarta.validation.Valid;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/servicos")
public class ServicoResource {

	@Autowired
	private ServicoService service;
	
	
	/*
	 * Consulta um servico pelo ID
	 */
	@GetMapping(value = "/{id}")
	public ResponseEntity<ServicoDTO> findById(@PathVariable Integer id) {
		ServicoDTO objDTO = new ServicoDTO(service.findById(id));
		return ResponseEntity.ok().body(objDTO);
	}
	
	
	/*
	 * Lista todos os servico
	 */
	@GetMapping
	public ResponseEntity<List<ServicoDTO>> findAll() {
		List<ServicoDTO> listDTO = service.findAll().stream().map(obj -> new ServicoDTO(obj))
				.collect(Collectors.toList());

		return ResponseEntity.ok().body(listDTO);
	}
	
	
	/*
	 * Cadastra um servico
	 */
	@PostMapping
	public ResponseEntity<ServicoDTO> create(@Valid @RequestBody ServicoDTO objDTO){
		Servico newObj = service.create(objDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
	
		return ResponseEntity.created(uri).build();
	}
	
	
	/*
	 * Atualiza um Servico
	 */
	@PutMapping(value = "/{id}")
	public ResponseEntity<ServicoDTO> update(@PathVariable Integer id,@Valid @RequestBody ServicoDTO objDTO){
		ServicoDTO newObj = new ServicoDTO(service.update(id, objDTO));
		return ResponseEntity.ok().body(newObj);
	}
	
	/*
	 * Deleta um Servico
	 */
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
