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

import com.francisco.os.domain.Tecnico;
import com.francisco.os.dtos.TecnicoDTO;
import com.francisco.os.services.TecnicoService;

import jakarta.validation.Valid;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/tecnicos")
public class TecnicoResource {

	@Autowired
	private TecnicoService service;
	
	
	/*
	 * Consulta um tecnico pelo ID
	 */
	@GetMapping(value = "/{id}")
	public ResponseEntity<TecnicoDTO> findById(@PathVariable Integer id) {
		TecnicoDTO objDTO = new TecnicoDTO(service.findById(id));
		return ResponseEntity.ok().body(objDTO);
	}
	
	
	/*
	 * Lista todos os tecnicos
	 */
	@GetMapping
	public ResponseEntity<List<TecnicoDTO>> findAll() {
		List<TecnicoDTO> listDTO = service.findAll().stream().map(obj -> new TecnicoDTO(obj))
				.collect(Collectors.toList());

		return ResponseEntity.ok().body(listDTO);
	}
	
	
	/*
	 * Cadastra um tecnico
	 */
	@PostMapping
	public ResponseEntity<TecnicoDTO> create(@Valid @RequestBody TecnicoDTO objDTO){
		Tecnico newObj = service.create(objDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
	
		return ResponseEntity.created(uri).build();
	}
	
	
	/*
	 * Atualiza um Tecnico
	 */
	@PutMapping(value = "/{id}")
	public ResponseEntity<TecnicoDTO> update(@PathVariable Integer id,@Valid @RequestBody TecnicoDTO objDTO){
		TecnicoDTO newObj = new TecnicoDTO(service.update(id, objDTO));
		return ResponseEntity.ok().body(newObj);
	}
	
	/*
	 * Deleta um Tecnico
	 */
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
