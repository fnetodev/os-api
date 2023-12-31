package com.francisco.os.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.francisco.os.domain.Pessoa;
import com.francisco.os.domain.Tecnico;
import com.francisco.os.dtos.TecnicoDTO;
import com.francisco.os.repository.PessoaRepository;
import com.francisco.os.repository.TecnicoRepository;
import com.francisco.os.services.exceptions.DataIntegrationException;
import com.francisco.os.services.exceptions.ObjectNotFoundException;

import jakarta.validation.Valid;

@Service
public class TecnicoService {

	@Autowired
	private TecnicoRepository repository;
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	
	public Tecnico findById(Integer id) {
		Optional<Tecnico> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + " Tipo: " + Tecnico.class.getName()));
	}

	public List<Tecnico> findAll() {
		return repository.findAll();
	}
	
	public Tecnico create(TecnicoDTO objDTO) {
		if(findByCPF(objDTO) != null) {
			throw new DataIntegrationException("CPF já cadastrado na base de dados");
		}
		return repository.save(new Tecnico(null, objDTO.getNome(),objDTO.getCpf(), objDTO.getTelefone()));
	}
	
	public Tecnico update(Integer id, @Valid TecnicoDTO objDTO) {

		Tecnico oldObj = findById(id);
		
		if(findByCPF(objDTO)!= null && findByCPF(objDTO).getId() != id) {
			throw new DataIntegrationException("CPF já cadastrado na base de dados");
		}
		
		
		oldObj.setNome(objDTO.getNome());
		oldObj.setCpf(objDTO.getCpf());
		oldObj.setTelefone(objDTO.getTelefone());
		
		return repository.save(oldObj);
	}
	
	public void delete(Integer id) {
		Tecnico obj = findById(id);
		if(obj.getList().size() > 0) {
			throw new DataIntegrationException("Técnico possui ordens de serviço, não pode ser deletado.");
		}
		
		repository.deleteById(id);
	}
	
	
	private Pessoa findByCPF(TecnicoDTO objDTO) {
		Pessoa obj = pessoaRepository.findByCPF(objDTO.getCpf());
		if(obj != null) {
			return obj;
			
		}
		return null;
	}

	

	
}
