package com.senai.aluguel_veiculos_api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.senai.aluguel_veiculos_api.model.Aluguel;
import com.senai.aluguel_veiculos_api.repository.AluguelRepository;

@Service
public class AluguelService {
	@Autowired
	private AluguelRepository aluguelRepository;
	
	public Aluguel insert(Aluguel aluguel) {
		return this.aluguelRepository.save(aluguel);
	}
	
	public void delete(Long id) {
		Aluguel aluguel = aluguelRepository.findById(id)
	            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Aluguel não encontrado"));
		aluguelRepository.delete(aluguel);
	}
	
	public Aluguel update(Aluguel aluguel) {
		Aluguel aluguelExistente = aluguelRepository.findById(aluguel.getId())
			    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Aluguel não encontrado"));
		return this.aluguelRepository.save(aluguelExistente);
	}
	
	public Aluguel findById(Long id) {
		return aluguelRepository.findById(id)
	            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Aluguel não encontrado"));
	}
	
	public List<Aluguel> findAll(){
		return this.aluguelRepository.findAll();
	}
}
