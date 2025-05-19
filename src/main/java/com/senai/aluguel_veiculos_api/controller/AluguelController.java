package com.senai.aluguel_veiculos_api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.senai.aluguel_veiculos_api.dtos.AluguelDto;
import com.senai.aluguel_veiculos_api.model.Aluguel;
import com.senai.aluguel_veiculos_api.service.AluguelService;

@RestController
@RequestMapping("/aluguel")
public class AluguelController {

    @Autowired
    private AluguelService aluguelService;
	
    @PostMapping
    public Aluguel registrarAluguel(@RequestBody AluguelDto dto) {
        return aluguelService.registrarAluguel(
            dto.getClienteId(),
            dto.getVeiculoId(),
            dto.getDataInicio(),
            dto.getDataFim()
        );
    }
    
    @GetMapping("/{id}")
	public Aluguel findById(@PathVariable Long id) {
		return aluguelService.findById(id);
	}
    
    @GetMapping
	public List<Aluguel> listarAlugueis() {
		return aluguelService.findAll();
	}
    
    @DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		aluguelService.delete(id);
	}
    
    @GetMapping("/ativos")
    public List<Aluguel> listarAlugueisAtivos() {
        return aluguelService.findAlugueisAtivos();
    }

    @PostMapping("/{id}/retornar")
    public void devolverVeiculo(@PathVariable Long id) {
        aluguelService.retornarVeiculo(id);
    }
}
