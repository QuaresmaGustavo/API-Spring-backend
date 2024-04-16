package com.example.carros.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.example.carros.domain.Entity.Proprietario;
import com.example.carros.domain.Entity.Veiculo;
import com.example.carros.domain.Repository.ProprietarioRepository;
import com.example.carros.domain.Repository.VeiculoRepository;
import com.example.carros.domain.Request.RequestVeiculo;
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
    private VeiculoRepository veiculoRepository;

    @Autowired
    private ProprietarioRepository proprietarioRepository;

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
    public ResponseEntity<Veiculo> cadastrar(@RequestBody @Validated RequestVeiculo dataVeiculo, @PathVariable("id") Integer id){
        Proprietario idProprietario = proprietarioRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Proprietario não encontrado"));
        Veiculo novoVeiculo = new Veiculo(dataVeiculo, idProprietario);
        Veiculo salvarNovoVeiculo = veiculoService.insertVeiculo(novoVeiculo);

        return ResponseEntity.ok().body(salvarNovoVeiculo);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Veiculo> atualizarVeiculo(@PathVariable Integer id, @RequestBody @Validated RequestVeiculo dados){
        Veiculo atualizarVeiculo = veiculoService.updateVeiculo(id, dados);
        return ResponseEntity.ok().body(atualizarVeiculo);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Veiculo> deletarVeiculo(@PathVariable Integer id){
        veiculoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}