package com.gerenciador.tarefas.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gerenciador.tarefas.entity.Role;
import com.gerenciador.tarefas.entity.Usuario;

@Repository
public interface IRoleRepository extends JpaRepository<Role, Long>{

	Usuario findByName(String username);
	
}
