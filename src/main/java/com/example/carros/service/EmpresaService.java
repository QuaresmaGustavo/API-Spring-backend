package com.example.carros.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.carros.domain.Entity.Empresa;
import com.example.carros.domain.Repository.EmpresaRepository;
import com.example.carros.domain.Request.EmpresaRequestDTO;

@Service
public class EmpresaService {
    @Autowired
    private EmpresaRepository repository;

    public List<Empresa> findAll() {
        return repository.findAll();
    }

    public Optional<Empresa> buscarPorId(Integer id) {
        return repository.findById(id);
    }

    public Empresa cadastrarEmpresa(EmpresaRequestDTO entity) {
        Empresa empresa = new Empresa(entity);
        return repository.save(empresa);
    }
    
    public Empresa atualizarEmpresa(EmpresaRequestDTO dados, Integer id){
        Empresa empresa = repository.findById(id).orElseThrow(()-> new IllegalArgumentException("Empresa não encontrada!"));

        empresa.setNome(dados.nome());
        empresa.setImagem(dados.imagem());
        repository.save(empresa);
        return empresa;
    }
}
