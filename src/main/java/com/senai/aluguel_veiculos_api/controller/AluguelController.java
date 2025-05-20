package com.senai.aluguel_veiculos_api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.senai.aluguel_veiculos_api.dtos.AluguelDto;
import com.senai.aluguel_veiculos_api.model.Aluguel;
import com.senai.aluguel_veiculos_api.model.Cliente;
import com.senai.aluguel_veiculos_api.model.Veiculo;
import com.senai.aluguel_veiculos_api.service.AluguelService;
import com.senai.aluguel_veiculos_api.service.ClienteService;
import com.senai.aluguel_veiculos_api.service.VeiculoService;

@RestController
@RequestMapping("/aluguel")
@CrossOrigin(origins = "*")
public class AluguelController {

    @Autowired
    private AluguelService aluguelService;
    @Autowired
    private ClienteService clienteService;
    @Autowired
    private VeiculoService veiculoService;
	
    @PostMapping
    public ResponseEntity<Aluguel> registrarAluguel(@RequestBody AluguelDto dto) {
    	Aluguel aluguel = aluguelService.registrarAluguel(
                dto.getClienteId(),
                dto.getVeiculoId(),
                dto.getDataInicio(),
                dto.getDataFim()
        );
    	return ResponseEntity.ok(aluguel);
    }
    
    @GetMapping("/{id}")
	public ResponseEntity<Aluguel> findById(@PathVariable Long id) {
    	
		return ResponseEntity.ok(aluguelService.findById(id));
	}
    
    @GetMapping
	public ResponseEntity<List<Aluguel>> listarAlugueis() {
		return ResponseEntity.ok(aluguelService.findAll());
	}
    
    @DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
    	if (id != null) {
            aluguelService.delete(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
	}
    
    @PutMapping
	public ResponseEntity<Aluguel> update(@RequestBody AluguelDto dto) {
    	Aluguel aluguel = aluguelService.findById(dto.getId());
    	if (aluguel == null) {
    		return ResponseEntity.notFound().build();
    	}
    	
    	Veiculo veiculo = veiculoService.findById(dto.getVeiculoId());
    	Cliente cliente = clienteService.findById(dto.getClienteId());
    	aluguel.setVeiculo(veiculo);
    	aluguel.setCliente(cliente);
    	aluguel.setDataInicio(dto.getDataInicio());
    	aluguel.setDataFim(dto.getDataFim());
		aluguelService.update(aluguel);
        return ResponseEntity.ok(aluguel);
	}
}
