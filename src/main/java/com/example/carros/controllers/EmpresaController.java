package com.example.carros.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.example.carros.domain.Entity.Empresa;
import com.example.carros.domain.Repository.EmpresaRepository;
import com.example.carros.domain.Request.EmpresaRequestDTO;
import com.example.carros.service.EmpresaService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping("/empresa")
@CrossOrigin(origins = "http://localhost:8080")
public class EmpresaController {
    @Autowired
    private EmpresaService service;

    @Autowired
    private EmpresaRepository repository;

    @GetMapping("/todos")
    public ResponseEntity<List<Empresa>> buscarTodasEmpresas() {
        return ResponseEntity.ok().body(service.findAll());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Empresa>> buscarEmpresa(@PathVariable Integer id) {
        return ResponseEntity.ok().body(service.buscarPorId(id));
    }
    
    @PostMapping("/cadastrar")
    public ResponseEntity<Empresa> cadastrarEmpresa(@RequestBody EmpresaRequestDTO entity) {
        return ResponseEntity.ok().body(service.cadastrarEmpresa(entity));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Empresa> atualizarEmpresa(@PathVariable Integer id, @RequestBody EmpresaRequestDTO entity) {
        return ResponseEntity.ok().body(service.atualizarEmpresa(entity, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Empresa> deletar(@PathVariable("id") Integer id){
        repository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
