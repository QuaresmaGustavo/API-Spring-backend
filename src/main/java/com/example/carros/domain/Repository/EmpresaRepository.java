package com.example.carros.domain.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.carros.domain.Entity.Empresa;

public interface EmpresaRepository extends JpaRepository<Empresa, Integer>{}
