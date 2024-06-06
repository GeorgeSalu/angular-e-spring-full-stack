package com.gerenciador.tarefas.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gerenciador.tarefas.entity.Tarefa;

@Repository
public interface IGerenciadortarefasRepository extends JpaRepository<Tarefa, Long> {

	Page<Tarefa> findByTituloContaining(String titulo,Pageable pageable);
	
	Page<Tarefa> findAll(Pageable pageable);
	
}
