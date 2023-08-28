package com.francisco.os.services;

import java.time.LocalDateTime;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import com.francisco.os.domain.Cliente;
import com.francisco.os.domain.OS;
import com.francisco.os.domain.Servico;
import com.francisco.os.domain.Tecnico;
import com.francisco.os.domain.enuns.Prioridade;
import com.francisco.os.domain.enuns.Status;
import com.francisco.os.repository.ClienteRepository;
import com.francisco.os.repository.OSRepository;
import com.francisco.os.repository.ServicoRepository;
import com.francisco.os.repository.TecnicoRepository;

@Service
public class DBService {
	
	
	@Autowired
	private TecnicoRepository tecnicoRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private ServicoRepository servicoRepository;
	
	@Autowired
	private OSRepository osRepository;
	
	
	
	
	public void instanciaDB() {
		Tecnico t1 = new Tecnico (null, "Tecnico 01","742.931.150-48","(61) 98888-8888");
		Cliente c1 = new Cliente (null, "Cliente 01","099.375.010-91","(61) 98888-7777");
		Servico s1 = new Servico (null, "Servico 01");
		OS os1 = new OS(null, Prioridade.ALTA, "Teste create OS", Status.ANDAMENTO,s1, t1, c1);
		
		
		
		t1.getList().add(os1);
		c1.getList().add(os1);
		
		tecnicoRepository.saveAll(Arrays.asList(t1));
		clienteRepository.saveAll(Arrays.asList(c1));
		servicoRepository.saveAll(Arrays.asList(s1));
		
		osRepository.saveAll(Arrays.asList(os1));
	}

}
