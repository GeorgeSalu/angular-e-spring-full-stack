package com.gerenciador.tarefas.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.gerenciador.tarefas.status.TarefaStatusEnum;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@Entity
@Table(name = "tarefas")
public class Tarefa implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	
	@Column(nullable = false)
	private String titulo;
	
	@Column(nullable = false)
	private String descricao;
	
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private TarefaStatusEnum status;
	
	@Column(nullable = false)
	private Usuario responsavel;
	
	@Column(nullable = false)
	private Usuario criador;
	
	@Column(nullable = false)
	private int quantidadeHorasEstimadas;
	
	@Column
	private Integer quantidadeHorasRealizadas;
	
	@Column
	@CreationTimestamp
	private LocalTime dataCadastro;
	
	@Column
	@UpdateTimestamp
	private LocalTime dataAtualizacao;
	
	@Column
	private LocalTime tempoRealizado;
}
