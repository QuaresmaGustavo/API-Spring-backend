package com.example.carros.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.example.carros.domain.proprietario.Proprietario;
import com.example.carros.domain.proprietario.ProprietarioRepository;
import com.example.carros.domain.veiculos.RequestVeiculo;
import com.example.carros.domain.veiculos.Veiculo;
import com.example.carros.domain.veiculos.VeiculoRepository;
import com.example.carros.service.VeiculoService;

import java.util.List;

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
public class VeiculoController {

    @Autowired
    private VeiculoRepository veiculoRepository;

    @Autowired
    private ProprietarioRepository proprietarioRepository;

    @Autowired
    private VeiculoService veiculoService;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping(path = "/all")
    public ResponseEntity<List<Veiculo>> findAll() {
        List<Veiculo> listaVeiculos = veiculoService.findAll();
        return ResponseEntity.ok().body(listaVeiculos);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping(path = "/{id}")
    public ResponseEntity<Veiculo> cadastrar(@RequestBody @Validated RequestVeiculo dataVeiculo, @PathVariable("id") Integer id){
        Proprietario idProprietario = proprietarioRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Proprietario não encontrado"));
        Veiculo novoVeiculo = new Veiculo(dataVeiculo, idProprietario);
        Veiculo salvarNovoVeiculo = veiculoService.insertVeiculo(novoVeiculo);

        return ResponseEntity.ok().body(salvarNovoVeiculo);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PutMapping(path = "/{id}")
    public ResponseEntity<Veiculo> atualizarVeiculo(@PathVariable Integer id, @RequestBody @Validated RequestVeiculo dados){
        Veiculo atualizarVeiculo = veiculoService.updateVeiculo(id, dados);
        return ResponseEntity.ok().body(atualizarVeiculo);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Veiculo> deletarVeiculo(@PathVariable Integer id){
        veiculoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}