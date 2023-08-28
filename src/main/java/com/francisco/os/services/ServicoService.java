package com.francisco.os.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.francisco.os.domain.Servico;
import com.francisco.os.dtos.ServicoDTO;
import com.francisco.os.repository.ServicoRepository;
import com.francisco.os.services.exceptions.DataIntegrationException;
import com.francisco.os.services.exceptions.ObjectNotFoundException;

import jakarta.validation.Valid;

@Service
public class ServicoService {

	@Autowired
	private ServicoRepository repository;

	

	public Servico findById(Integer id) {
		Optional<Servico> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + " Tipo: " + Servico.class.getName()));
	}

	public List<Servico> findAll() {
		return repository.findAll();
	}

	public Servico create(ServicoDTO objDTO) {

		return repository.save(new Servico(null, objDTO.getNome()));
	}

	public Servico update(Integer id, @Valid ServicoDTO objDTO) {

		Servico oldObj = findById(id);

		oldObj.setNome(objDTO.getNome());

		return repository.save(oldObj);
	}

	public void delete(Integer id) {
		Servico obj = findById(id);
		if (obj.getList().size() > 0) {
			throw new DataIntegrationException("Serviço possui ordens de serviço, não pode ser deletado.");
		}

		repository.deleteById(id);
	}

}
