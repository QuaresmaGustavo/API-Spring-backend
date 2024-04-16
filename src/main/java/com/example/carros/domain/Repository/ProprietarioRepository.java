package com.example.carros.domain.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.carros.domain.Entity.Proprietario;

public interface ProprietarioRepository extends JpaRepository<Proprietario, Integer>{}