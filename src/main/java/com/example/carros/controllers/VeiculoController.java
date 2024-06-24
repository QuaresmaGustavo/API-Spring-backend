package com.example.carros.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.example.carros.domain.Entity.Veiculo;
import com.example.carros.domain.Repository.IVeiculoRepository;
import com.example.carros.domain.Request.VeiculoRequestDTO;
import com.example.carros.service.VeiculoService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/veiculo")
@CrossOrigin(origins = "http://localhost:8080")
public class VeiculoController {

    @Autowired
    private IVeiculoRepository veiculoRepository;

    @Autowired
    private VeiculoService veiculoService;

    @GetMapping(path = "/all")
    public ResponseEntity<List<Veiculo>> findAll() {
        List<Veiculo> listaVeiculos = veiculoService.findAll();
        return ResponseEntity.ok().body(listaVeiculos);
    }

    @GetMapping(path="/{id}")
    public ResponseEntity<Optional<Veiculo>> findById(@PathVariable Integer id) {
        Optional<Veiculo> veiculo = veiculoService.findById(id);
        return ResponseEntity.ok().body(veiculo);
    }
    
    @PostMapping(path = "/{id}")
    public ResponseEntity<Veiculo> cadastrar(@RequestBody @Validated VeiculoRequestDTO dataVeiculo, @PathVariable("id") Integer id){
        return ResponseEntity.ok().body(veiculoService.insertVeiculo(dataVeiculo, id));
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Veiculo> atualizarVeiculo(@PathVariable Integer id, @RequestBody @Validated VeiculoRequestDTO dados){
        Veiculo veiculo = veiculoService.atualizarVeiculo(id, dados);
        return ResponseEntity.ok().body(veiculo);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Veiculo> deletarVeiculo(@PathVariable Integer id){
        veiculoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}