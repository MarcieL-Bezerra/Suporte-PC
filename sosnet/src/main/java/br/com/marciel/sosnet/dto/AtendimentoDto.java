package br.com.marciel.sosnet.dto;

import java.time.LocalDateTime;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.marciel.sosnet.model.Usuario;
import br.com.marciel.sosnet.model.utiu.Setor;
import br.com.marciel.sosnet.model.utiu.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AtendimentoDto extends RepresentationModel<AtendimentoDto> {
	private Long id;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime dataAbertura = LocalDateTime.now();
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime dataFechamento;
	private Status status = Status.ABERTO;
	private Usuario solicitante;
	private Setor setor;
	private String descricao;
	private Usuario tecnico;
}
