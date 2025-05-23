package com.senai.aluguel_veiculos_api.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.senai.aluguel_veiculos_api.model.Veiculo;

@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo, Long>{
	List<Veiculo> findByDisponivelTrue();
}
