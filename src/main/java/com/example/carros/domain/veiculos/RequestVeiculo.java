package com.example.carros.domain.veiculos;

import com.example.carros.domain.proprietario.Proprietario;

public record RequestVeiculo(Integer id, Proprietario proprietario, String nome , Integer ano, String montadora, String placa, String tipo, String imagem) {}