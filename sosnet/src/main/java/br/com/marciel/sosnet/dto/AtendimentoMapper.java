package br.com.marciel.sosnet.dto;

import br.com.marciel.sosnet.model.Atendimento;

public class AtendimentoMapper {
	public static AtendimentoDto consultaAtendimento(Atendimento atendimento) {
		AtendimentoDto atendimentoDtoRecebido = new AtendimentoDto(atendimento.getId(), atendimento.getDataAbertura(),
				atendimento.getDataFechamento(), atendimento.getStatus(), atendimento.getSolicitante(),
				atendimento.getSetor(), atendimento.getDescricao(), atendimento.getTecnico());
		atendimento.getSolicitante().setSenha("**##***#*");
		atendimento.getTecnico().setSenha("**##***#*");
		return atendimentoDtoRecebido;
	}

	public static Atendimento fromDto(AtendimentoDto dto) {
		return new Atendimento(null, dto.getDataAbertura(), dto.getDataFechamento(), dto.getStatus(),
				dto.getSolicitante(), dto.getSetor(), dto.getDescricao(), dto.getTecnico());

	}
}
