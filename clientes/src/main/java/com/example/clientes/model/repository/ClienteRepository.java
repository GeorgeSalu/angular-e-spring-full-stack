package com.example.clientes.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.clientes.model.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer>{
	
}
