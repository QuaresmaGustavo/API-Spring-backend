package com.example.carros.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.example.carros.domain.proprietario.Proprietario;
import com.example.carros.domain.proprietario.ProprietarioRepository;
import com.example.carros.domain.proprietario.RequestProprietario;
import com.example.carros.service.ProprietarioService;

import java.util.List;

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
public class ProprietarioController {
    @Autowired
    private ProprietarioRepository propRepository;

    @Autowired
    private ProprietarioService propService;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping(path = "/all")
    public ResponseEntity<List<Proprietario>> findAll() {
        List<Proprietario> listaProps = propService.findAll();
        return ResponseEntity.ok().body(listaProps);
    }
    
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping(path = "/cadastrar")
    public ResponseEntity<Proprietario> cadastrar(@RequestBody @Validated RequestProprietario dataProprietario) {
        Proprietario novoProprietario = new Proprietario(dataProprietario);
        propRepository.save(novoProprietario);
        return ResponseEntity.ok().body(novoProprietario);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PutMapping(path = "/{id}")
    public ResponseEntity<Proprietario> atualizarProprietario(@PathVariable Integer id, @RequestBody 
                                                              @Validated RequestProprietario dados){
        Proprietario atualizarProprietario = propService.updateProprietario(id, dados);
        return ResponseEntity.ok().body(atualizarProprietario);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Proprietario> deletarProprietario(@PathVariable Integer id){
        propRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}