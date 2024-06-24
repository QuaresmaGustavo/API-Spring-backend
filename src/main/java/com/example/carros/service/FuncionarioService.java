package com.example.carros.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.carros.domain.Entity.Empresa;
import com.example.carros.domain.Entity.Funcionario;
import com.example.carros.domain.Repository.FuncionarioRepository;
import com.example.carros.domain.Repository.EmpresaRepository;
import com.example.carros.domain.Request.FuncionarioRequestDTO;

@Service
public class FuncionarioService {
    @Autowired private FuncionarioRepository repository;

    @Autowired
    private EmpresaRepository eRepository;

    public List<Funcionario> findAll(){
        return repository.findAll();
    }

    public Optional<Funcionario> findById(Integer id){
        return repository.findById(id);
    }

    public Funcionario cadastrarFuncionario(FuncionarioRequestDTO datafuncionario, Integer idEmpresa) {
        Empresa id = eRepository.findById(idEmpresa).orElseThrow(() -> new IllegalArgumentException("Empresa não encontrado"));
        Funcionario novoFuncionario = new Funcionario(datafuncionario, id);
        return repository.save(novoFuncionario);
    }

    public Funcionario atualizarFuncionario(Integer id, FuncionarioRequestDTO dados){
        Funcionario funcionario = repository.findById(id).orElseThrow(()-> new IllegalArgumentException("Funcionario não encontrado"));

            funcionario.setNome(dados.nome());
            return repository.save(funcionario);
    }
}
