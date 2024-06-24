package com.example.carros.domain.Request;

import com.example.carros.domain.Entity.Funcionario;

public record VeiculoRequestDTO(Integer id, Funcionario funcionario, String nome , Integer ano, String montadora, String placa, String cor, String imagem) {}