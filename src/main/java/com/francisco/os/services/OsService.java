package com.francisco.os.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.stereotype.Service;

import com.francisco.os.domain.Cliente;
import com.francisco.os.domain.OS;
import com.francisco.os.domain.Servico;
import com.francisco.os.domain.Tecnico;
import com.francisco.os.domain.enuns.Prioridade;
import com.francisco.os.domain.enuns.Status;
import com.francisco.os.dtos.OSDTO;
import com.francisco.os.dtos.ServicoDTO;
import com.francisco.os.repository.OSRepository;
import com.francisco.os.services.exceptions.DataIntegrationException;
import com.francisco.os.services.exceptions.ObjectNotFoundException;

import jakarta.validation.Valid;

@Service
public class OsService {

	@Autowired
	private OSRepository repository;
	
	@Autowired
	private TecnicoService tecnicoService;
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private ServicoService servicoService;
	

	public OS findById(Integer id) {
		Optional<OS> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + OS.class.getName()));
		
	}
	
	public List<OS> findAll() {
		return repository.findAll();
		
	}

	public OS create(@Valid OSDTO obj) {
		
		OS newObj = fromDTO(obj);
		newObj.setDataAbertura(LocalDateTime.now());
		
		return repository.save(newObj);
	}
	
	
	public OS update(@Valid OSDTO obj) {


		findById(obj.getId());
		
		OS newObj = fromDTO(obj);
		
		return repository.save(newObj);
		
		
	}
	
	private OS fromDTO(OSDTO obj) {
		
		Servico servico = servicoService.findById(obj.getServico());
		Tecnico tecnico = tecnicoService.findById(obj.getTecnico());
		Cliente cliente = clienteService.findById(obj.getCliente());
		
		
		
		OS newObj = new OS();
		try {
		newObj.setDataAbertura(findById(obj.getId()).getDataAbertura());
		} catch (InvalidDataAccessApiUsageException ex) { 
			}
		
		newObj.setId(obj.getId());
		
		newObj.setObservacoes(obj.getObservacoes());
		newObj.setPrioridade(Prioridade.toEnum(obj.getPrioridade()));
		newObj.setStatus(Status.toEnum(obj.getStatus()));
		
		newObj.setServico(servico);
		newObj.setTecnico(tecnico);
		newObj.setCliente(cliente);
		
		if(newObj.getStatus().getCod().equals(2)) {
			newObj.setDataFechamento(LocalDateTime.now());
		}
		
		
		
		return newObj;
	}
	

}
