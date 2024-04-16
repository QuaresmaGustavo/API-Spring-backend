package com.example.carros.domain.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.carros.domain.Entity.Veiculo;

public interface VeiculoRepository extends JpaRepository<Veiculo, Integer>{}
