package com.example.carros.domain.Entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.example.carros.domain.Request.FuncionarioRequestDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="funcionario")
@Getter
@Setter
public class Funcionario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;

    private LocalDateTime data;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_empresa")
    private Empresa id_empresa;

    @OneToMany(mappedBy = "funcionario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Veiculo> veiculos = new ArrayList<>();

    public Funcionario() {}

    public Funcionario(FuncionarioRequestDTO data, Empresa id) {
        this.id_empresa = id;
        this.nome = data.nome();
        this.data = LocalDateTime.now();
    }
}