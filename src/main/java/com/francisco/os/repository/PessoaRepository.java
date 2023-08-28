package com.francisco.os.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.francisco.os.domain.Pessoa;
import com.francisco.os.domain.Tecnico;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Integer>{

	@Query("Select obj FROM Pessoa obj WHERE obj.cpf =:cpf ")
	Pessoa findByCPF(@Param("cpf") String cpf);

}
