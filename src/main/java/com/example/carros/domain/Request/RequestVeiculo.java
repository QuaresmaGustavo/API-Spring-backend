package com.example.carros.domain.Request;

import com.example.carros.domain.Entity.Proprietario;

public record RequestVeiculo(Integer id, Proprietario proprietario, String nome , Integer ano, String montadora, String placa, String cor, String imagem) {}