package com.senai.aluguel_veiculos_api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.senai.aluguel_veiculos_api.model.Cliente;
import com.senai.aluguel_veiculos_api.repository.ClienteRepository;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	public Cliente insert(Cliente cliente) {
		return this.clienteRepository.save(cliente);
	}
	
	public void delete(Long id) {
		Cliente cliente = clienteRepository.findById(id)
	            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
		clienteRepository.delete(cliente);
	}
	
	public Cliente update(Cliente cliente) {
		Cliente clienteExistente = clienteRepository.findById(cliente.getId())
			    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
		return this.clienteRepository.save(clienteExistente);
	}
	
	public Cliente findById(Long id) {
		return clienteRepository.findById(id)
	            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
	}
	
	public List<Cliente> findAll(){
		return this.clienteRepository.findAll();
	}
}
