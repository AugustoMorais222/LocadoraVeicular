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

import com.senai.aluguel_veiculos_api.model.Cliente;
import com.senai.aluguel_veiculos_api.repository.ClienteRepository;

@RestController
@RequestMapping("/cliente")
public class ClienteController {
    @Autowired
    private ClienteRepository clienteRepository;

    @PostMapping
    public ResponseEntity<Cliente> insert(@RequestBody Cliente grupo) {
        Cliente novoCliente = clienteRepository.save(grupo);
        return ResponseEntity.ok(novoCliente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (clienteRepository.existsById(id)) {
            clienteRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping
    public ResponseEntity<Cliente> update(@RequestBody Cliente grupo) {
        if (clienteRepository.existsById(grupo.getId())) {
            Cliente atualizado = clienteRepository.save(grupo);
            return ResponseEntity.ok(atualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> findById(@PathVariable Long id) {
    	Cliente grupo = clienteRepository.findById(id).orElse(null);
    	if (grupo != null) {
    	    return ResponseEntity.ok(grupo);
    	} else {
    	    return ResponseEntity.notFound().build();
    	}
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> findAll() {
        List<Cliente> grupos = clienteRepository.findAll();
        return ResponseEntity.ok(grupos);
    }
}
