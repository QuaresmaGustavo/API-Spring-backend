package com.example.carros.domain.Entity;

import com.example.carros.domain.Request.VeiculoRequestDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "veiculo")
@Getter
@Setter
public class Veiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_funcionario")
    private Funcionario funcionario;

    private String nome, montadora, placa, cor, imagem;

    private Integer ano;

    public Veiculo(){}

    public Veiculo(VeiculoRequestDTO dataVeiculo, Funcionario id){
        this.funcionario = id;
        this.nome = dataVeiculo.nome();
        this.ano = dataVeiculo.ano();
        this.montadora = dataVeiculo.montadora();
        this.placa = dataVeiculo.placa();
        this.cor = dataVeiculo.cor();
        this.imagem = dataVeiculo.imagem();
    }
}
