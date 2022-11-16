package br.com.marciel.sosnet.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.marciel.sosnet.model.utiu.Setor;
import br.com.marciel.sosnet.model.utiu.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_atendimento")
public class Atendimento {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime dataAbertura = LocalDateTime.now();
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime dataFechamento;
	private Status status = Status.ABERTO;
	@NotNull
	@ManyToOne
	private Usuario solicitante;
	@NotNull
	private Setor setor;
	@NotNull
	private String descricao;
	@ManyToOne
	private Usuario tecnico;

}
