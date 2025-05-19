package com.senai.aluguel_veiculos_api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.senai.aluguel_veiculos_api.model.Veiculo;
import com.senai.aluguel_veiculos_api.repository.VeiculoRepository;

@RestController
@RequestMapping("/veiculo")
public class VeiculoController {
    @Autowired
    private VeiculoRepository veiculoRepository;

    @PostMapping
    public ResponseEntity<Veiculo> insert(@RequestBody Veiculo grupo) {
        Veiculo novoVeiculo = veiculoRepository.save(grupo);
        return ResponseEntity.ok(novoVeiculo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (veiculoRepository.existsById(id)) {
            veiculoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping
    public ResponseEntity<Veiculo> update(@RequestBody Veiculo grupo) {
        if (veiculoRepository.existsById(grupo.getId())) {
            Veiculo atualizado = veiculoRepository.save(grupo);
            return ResponseEntity.ok(atualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Veiculo> findById(@PathVariable Long id) {
    	Veiculo grupo = veiculoRepository.findById(id).orElse(null);
    	if (grupo != null) {
    	    return ResponseEntity.ok(grupo);
    	} else {
    	    return ResponseEntity.notFound().build();
    	}
    }

    @GetMapping
    public ResponseEntity<List<Veiculo>> findAll() {
        List<Veiculo> grupos = veiculoRepository.findAll();
        return ResponseEntity.ok(grupos);
    }
}
