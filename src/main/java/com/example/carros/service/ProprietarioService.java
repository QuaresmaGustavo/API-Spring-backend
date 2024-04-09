package com.example.carros.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import com.example.carros.domain.proprietario.Proprietario;
import com.example.carros.domain.proprietario.ProprietarioRepository;
import com.example.carros.domain.proprietario.RequestProprietario;

@Service
public class ProprietarioService {
    @Autowired private ProprietarioRepository repository;

    public List<Proprietario> findAll(){
        return repository.findAll();
    }

    public Proprietario updateProprietario(Integer id, RequestProprietario dados){
        Optional<Proprietario> idProprietario = repository.findById(id);

        if (idProprietario.isPresent()) {
            Proprietario newProprietario = idProprietario.get();

            newProprietario.setNome(dados.nome());
            return repository.save(newProprietario);
        }else{
            throw new ResourceAccessException("Proprietario não encontrado");
        }
    }
}
