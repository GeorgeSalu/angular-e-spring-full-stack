package com.example.agendaapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.agendaapi.model.Contato;

public interface ContatoRepository extends JpaRepository<Contato, Integer>{

}
