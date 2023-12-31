package com.francisco.os.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.francisco.os.domain.Pessoa;
import com.francisco.os.domain.Cliente;
import com.francisco.os.dtos.ClienteDTO;
import com.francisco.os.repository.PessoaRepository;
import com.francisco.os.repository.ClienteRepository;
import com.francisco.os.services.exceptions.DataIntegrationException;
import com.francisco.os.services.exceptions.ObjectNotFoundException;

import jakarta.validation.Valid;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repository;
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	
	public Cliente findById(Integer id) {
		Optional<Cliente> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + " Tipo: " + Cliente.class.getName()));
	}

	public List<Cliente> findAll() {
		return repository.findAll();
	}
	
	public Cliente create(ClienteDTO objDTO) {
		if(findByCPF(objDTO) != null) {
			throw new DataIntegrationException("CPF já cadastrado na base de dados");
		}
		return repository.save(new Cliente(null, objDTO.getNome(),objDTO.getCpf(), objDTO.getTelefone()));
	}
	
	public Cliente update(Integer id, @Valid ClienteDTO objDTO) {

		Cliente oldObj = findById(id);
		
		if(findByCPF(objDTO)!= null && findByCPF(objDTO).getId() != id) {
			throw new DataIntegrationException("CPF já cadastrado na base de dados");
		}
		
		
		oldObj.setNome(objDTO.getNome());
		oldObj.setCpf(objDTO.getCpf());
		oldObj.setTelefone(objDTO.getTelefone());
		
		return repository.save(oldObj);
	}
	
	public void delete(Integer id) {
		Cliente obj = findById(id);
		if(obj.getList().size() > 0) {
			throw new DataIntegrationException("Cliente possui ordens de serviço, não pode ser deletado.");
		}
		
		repository.deleteById(id);
	}
	
	
	private Pessoa findByCPF(ClienteDTO objDTO) {
		Pessoa obj = pessoaRepository.findByCPF(objDTO.getCpf());
		if(obj != null) {
			return obj;
			
		}
		return null;
	}

	

	
}
