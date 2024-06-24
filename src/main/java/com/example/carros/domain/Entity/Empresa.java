package com.example.carros.domain.Entity;

import java.util.ArrayList;
import java.util.List;

import com.example.carros.domain.Request.EmpresaRequestDTO;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="empresa")
@Getter
@Setter
public class Empresa {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome, imagem;

    @OneToMany(mappedBy = "id_empresa", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Funcionario> funcionarios = new ArrayList<>();

    public Empresa(){}

    public Empresa(EmpresaRequestDTO dados){
        this.nome = dados.nome();
        this.imagem = dados.imagem();
    }
}
