package com.example.carros.domain.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.carros.domain.Entity.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Integer>{}