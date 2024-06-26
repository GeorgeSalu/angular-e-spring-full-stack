package com.gerenciador.tarefas.entity;

import java.io.Serializable;
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
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tarefas")
public class Tarefa implements Serializable {

	private static final long serialVersionUID = -3077120956588164195L;

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
	
	@Column()
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
	
}
