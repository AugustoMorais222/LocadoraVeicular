package com.senai.aluguel_veiculos_api.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.senai.aluguel_veiculos_api.model.Aluguel;

@Repository
public interface AluguelRepository extends JpaRepository<Aluguel, Long>{
	List<Aluguel> findByDataFimGreaterThanEqual(LocalDate data);

}
