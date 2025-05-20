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

import com.senai.aluguel_veiculos_api.model.Veiculo;
import com.senai.aluguel_veiculos_api.service.VeiculoService;

@RestController
@RequestMapping("/veiculo")
@CrossOrigin(origins = "*")
public class VeiculoController {
    @Autowired
    private VeiculoService veiculoService;

    @PostMapping
    public ResponseEntity<Veiculo> insert(@RequestBody Veiculo veiculo) {
        Veiculo novoVeiculo = veiculoService.insert(veiculo);
        return ResponseEntity.ok(novoVeiculo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (id != null) {
            veiculoService.delete(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping
    public ResponseEntity<Veiculo> update(@RequestBody Veiculo veiculo) {
		Veiculo atualizado = veiculoService.findById(veiculo.getId());
		
		if (atualizado == null) {
			return ResponseEntity.notFound().build();
		}
		
		atualizado.setModelo(veiculo.getModelo());
		atualizado.setDisponivel(veiculo.getDisponivel());
		atualizado.setTipo(veiculo.getTipo());
    	veiculoService.update(atualizado);
		return ResponseEntity.ok(atualizado);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Veiculo> findById(@PathVariable Long id) {
    	Veiculo veiculo = veiculoService.findById(id);
    	if (veiculo != null) {
    	    return ResponseEntity.ok(veiculo);
    	} else {
    	    return ResponseEntity.notFound().build();
    	}
    }

    @GetMapping
    public ResponseEntity<List<Veiculo>> findAll() {
        List<Veiculo> veiculos = veiculoService.findAll();
        return ResponseEntity.ok(veiculos);
    }
    
    @GetMapping("/ativos")
    public ResponseEntity<List<Veiculo>> findVeiculosAtivos() {
        return ResponseEntity.ok(veiculoService.findVeiculosDisponiveis());
    }
    
    @PostMapping("/{id}/retornar")
    public ResponseEntity<Void> devolverVeiculo(@PathVariable Long id) {
    	veiculoService.retornarVeiculo(id);
    	return ResponseEntity.noContent().build();
    }
}
