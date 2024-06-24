package com.example.carros.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.carros.domain.Entity.Funcionario;
import com.example.carros.domain.Entity.Veiculo;
import com.example.carros.domain.Repository.FuncionarioRepository;
import com.example.carros.domain.Repository.IVeiculoRepository;
import com.example.carros.domain.Request.VeiculoRequestDTO;

@Service
public class VeiculoService {
    @Autowired IVeiculoRepository repository;

    @Autowired
    private FuncionarioRepository fRepository;
    
    public List<Veiculo> findAll(){
        return repository.findAll();
    }

    public Optional<Veiculo> findById(Integer id){
        Optional<Veiculo> idVeiculo = repository.findById(id);
        return idVeiculo;
    }

    public Veiculo insertVeiculo(VeiculoRequestDTO dadosVeiculo, Integer id){
        Funcionario idProprietario = fRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Funcionario não encontrado"));
        Veiculo novoVeiculo = new Veiculo(dadosVeiculo, idProprietario);
        return repository.save(novoVeiculo);
    }

    public Veiculo atualizarVeiculo(Integer id, VeiculoRequestDTO dados){
        Veiculo veiculo = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Funcionario não encontrado"));

            veiculo.setNome(dados.nome());
            veiculo.setAno(dados.ano());
            veiculo.setMontadora(dados.montadora());
            veiculo.setPlaca(dados.placa());
            veiculo.setCor(dados.cor());
            veiculo.setImagem(dados.imagem());

            return repository.save(veiculo);
    }
}
