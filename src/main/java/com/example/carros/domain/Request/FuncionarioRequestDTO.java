package com.example.carros.domain.Request;

import java.time.LocalDateTime;

import com.example.carros.domain.Entity.Empresa;

public record FuncionarioRequestDTO(Integer id, Empresa id_Empresa, String nome, LocalDateTime data) {}
