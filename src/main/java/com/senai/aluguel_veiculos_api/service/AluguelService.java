package com.senai.aluguel_veiculos_api.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.senai.aluguel_veiculos_api.model.Aluguel;
import com.senai.aluguel_veiculos_api.model.Cliente;
import com.senai.aluguel_veiculos_api.model.Veiculo;
import com.senai.aluguel_veiculos_api.repository.AluguelRepository;
import com.senai.aluguel_veiculos_api.repository.ClienteRepository;
import com.senai.aluguel_veiculos_api.repository.VeiculoRepository;

@Service
public class AluguelService {
	@Autowired
	private AluguelRepository aluguelRepository;
	@Autowired
	private VeiculoRepository veiculoRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	
	
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
	
	public Aluguel registrarAluguel(Long clienteId, Long veiculoId, LocalDate inicio, LocalDate fim) {
        Veiculo veiculo = veiculoRepository.findById(veiculoId)
        		.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Veiculo não encontrado"));

        if (!veiculo.getDisponivel()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Veiculo não indisponivel");
        }

        Cliente cliente = clienteRepository.findById(clienteId)
        		.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));

        Aluguel aluguel = new Aluguel();
        aluguel.setCliente(cliente);
        aluguel.setVeiculo(veiculo);
        aluguel.setDataInicio(inicio);
        aluguel.setDataFim(fim);

        veiculo.setDisponivel(false);
        veiculoRepository.save(veiculo);

        return aluguelRepository.save(aluguel);
    }
	
}
