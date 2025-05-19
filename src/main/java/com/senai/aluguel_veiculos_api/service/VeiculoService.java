package com.senai.aluguel_veiculos_api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.senai.aluguel_veiculos_api.model.Veiculo;
import com.senai.aluguel_veiculos_api.repository.VeiculoRepository;

@Service
public class VeiculoService {
	@Autowired
	private VeiculoRepository veiculoRepository;
	
	public Veiculo insert(Veiculo veiculo) {
		return this.veiculoRepository.save(veiculo);
	}
	
	public void delete(Long id) {
		Veiculo veiculo = veiculoRepository.findById(id)
	            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Veiculo não encontrado"));
		veiculoRepository.delete(veiculo);
	}
	
	public Veiculo update(Veiculo veiculo) {
		Veiculo veiculoExistente = veiculoRepository.findById(veiculo.getId())
			    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Veiculo não encontrado"));
		return this.veiculoRepository.save(veiculoExistente);
	}
	
	public Veiculo findById(Long id) {
		return veiculoRepository.findById(id)
	            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Veiculo não encontrado"));
	}
	
	public List<Veiculo> findAll(){
		return this.veiculoRepository.findAll();
	}
}
