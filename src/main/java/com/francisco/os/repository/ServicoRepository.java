package com.francisco.os.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.francisco.os.domain.Servico;

@Repository
public interface ServicoRepository extends JpaRepository<Servico, Integer> {

	
}
