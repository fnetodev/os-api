package com.francisco.os.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.francisco.os.domain.OS;


@Repository
public interface OSRepository extends JpaRepository<OS, Integer> {

}
