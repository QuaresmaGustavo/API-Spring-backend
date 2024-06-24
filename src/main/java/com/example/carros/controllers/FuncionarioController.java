package com.example.carros.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.example.carros.domain.Entity.Funcionario;
import com.example.carros.domain.Repository.FuncionarioRepository;
import com.example.carros.domain.Request.FuncionarioRequestDTO;
import com.example.carros.service.FuncionarioService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/funcionario")
@CrossOrigin(origins = "http://localhost:8080")
public class FuncionarioController {
    @Autowired
    private FuncionarioRepository fRepository;

    @Autowired
    private FuncionarioService service;

    @GetMapping(path = "/all")
    public ResponseEntity<List<Funcionario>> findAll() {
        List<Funcionario> listaProps = service.findAll();
        return ResponseEntity.ok().body(listaProps);
    }

    @GetMapping(path="/{id}")
    public ResponseEntity<Optional<Funcionario>> findById(@PathVariable Integer id) {
        Optional<Funcionario> proprietario = service.findById(id);
        return ResponseEntity.ok(proprietario);
    }
    
    @PostMapping(path = "{idEmpresa}/cadastrar")
    public ResponseEntity<Funcionario> cadastrar(@RequestBody @Validated FuncionarioRequestDTO datafuncionario, @PathVariable("idEmpresa") Integer idEmpresa) {
        return ResponseEntity.ok().body(service.cadastrarFuncionario(datafuncionario, idEmpresa));
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Funcionario> atualizarProprietario(@PathVariable Integer id, @RequestBody 
                                                              @Validated FuncionarioRequestDTO dados){
        Funcionario funcionario = service.atualizarFuncionario(id, dados);
        return ResponseEntity.ok().body(funcionario);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Funcionario> deletarProprietario(@PathVariable Integer id){
        fRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}