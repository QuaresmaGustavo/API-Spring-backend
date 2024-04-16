package com.example.carros.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.example.carros.domain.Entity.Proprietario;
import com.example.carros.domain.Repository.ProprietarioRepository;
import com.example.carros.domain.Request.RequestProprietario;
import com.example.carros.service.ProprietarioService;

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
@RequestMapping("/proprietario")
@CrossOrigin(origins = "http://localhost:8080")
public class ProprietarioController {
    @Autowired
    private ProprietarioRepository propRepository;

    @Autowired
    private ProprietarioService propService;

    @GetMapping(path = "/all")
    public ResponseEntity<List<Proprietario>> findAll() {
        List<Proprietario> listaProps = propService.findAll();
        return ResponseEntity.ok().body(listaProps);
    }

    @GetMapping(path="/{id}")
    public ResponseEntity<Optional<Proprietario>> findById(@PathVariable Integer id) {
        Optional<Proprietario> proprietario = propService.findById(id);
        return ResponseEntity.ok(proprietario);
    }
    
    @PostMapping(path = "/cadastrar")
    public ResponseEntity<Proprietario> cadastrar(@RequestBody @Validated RequestProprietario dataProprietario) {
        Proprietario novoProprietario = new Proprietario(dataProprietario);
        propRepository.save(novoProprietario);
        return ResponseEntity.ok().body(novoProprietario);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Proprietario> atualizarProprietario(@PathVariable Integer id, @RequestBody 
                                                              @Validated RequestProprietario dados){
        Proprietario atualizarProprietario = propService.updateProprietario(id, dados);
        return ResponseEntity.ok().body(atualizarProprietario);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Proprietario> deletarProprietario(@PathVariable Integer id){
        propRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}