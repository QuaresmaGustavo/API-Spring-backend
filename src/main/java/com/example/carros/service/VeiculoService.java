package com.example.carros.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import com.example.carros.domain.Entity.Veiculo;
import com.example.carros.domain.Repository.VeiculoRepository;
import com.example.carros.domain.Request.RequestVeiculo;

@Service
public class VeiculoService {
    @Autowired VeiculoRepository repository;
    
    public List<Veiculo> findAll(){
        return repository.findAll();
    }

    public Optional<Veiculo> findById(Integer id){
        Optional<Veiculo> idVeiculo = repository.findById(id);
        return idVeiculo;
    }

    public Veiculo insertVeiculo(Veiculo newVeiculo){
        return repository.save(newVeiculo);
    }

    public Veiculo updateVeiculo(Integer id, RequestVeiculo dados){
        Optional<Veiculo> idVeiculo = repository.findById(id);

        if (idVeiculo.isPresent()) {
            
            Veiculo newVeiculo = idVeiculo.get();

            newVeiculo.setMontadora(dados.nome());
            newVeiculo.setAno(dados.ano());
            newVeiculo.setMontadora(dados.montadora());
            newVeiculo.setPlaca(dados.placa());
            newVeiculo.setCor(dados.cor());
            newVeiculo.setImagem(dados.imagem());

            return repository.save(newVeiculo);
        }else{
            throw new ResourceAccessException("Veiculo não encontrado");
        }
    }
}
