package br.com.marciel.sosnet.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.marciel.sosnet.dto.AtendimentoDto;
import br.com.marciel.sosnet.dto.AtendimentoMapper;
import br.com.marciel.sosnet.model.Atendimento;
import br.com.marciel.sosnet.service.AtendimentoService;


@RestController
@RequestMapping("/atendimentos")
public class AtendimentoController {
	@Autowired
	AtendimentoService atendimentoService;

	@GetMapping
	public ResponseEntity<Page<AtendimentoDto>> buscarTodosOsAtendimentos(@PageableDefault Pageable pageable) {
		Page<AtendimentoDto> atendimentoRecebidos = atendimentoService.listarTodosOsAtendimentos(pageable)
				.map(AtendimentoMapper::consultaAtendimento);
		for (AtendimentoDto atendimentoRecebido : atendimentoRecebidos) {
			Long id = atendimentoRecebido.getId();
			atendimentoRecebido.add(
					linkTo(methodOn(AtendimentoController.class).buscarAtendimentoPorId(pageable, id)).withSelfRel());

		}

		return ResponseEntity.ok(atendimentoRecebidos);
	}

	@PostMapping
	public ResponseEntity<AtendimentoDto> salvarAtendimentos(@RequestBody @Valid AtendimentoDto dto) {
		Atendimento atendimento = atendimentoService.salvarAtendimento(AtendimentoMapper.fromDto(dto));

		return ResponseEntity.ok(AtendimentoMapper.consultaAtendimento(atendimento));
	}

	@GetMapping("{id}")
	public ResponseEntity<AtendimentoDto> buscarAtendimentoPorId(@PageableDefault Pageable pageable,
			@PathVariable Long id) {
		Atendimento atendimento = atendimentoService.buscarAtendimento(id);

		AtendimentoDto atendimentoDto = AtendimentoMapper.consultaAtendimento(atendimento);
		atendimentoDto.add(linkTo(methodOn(AtendimentoController.class).buscarTodosOsAtendimentos(pageable))
				.withRel("Lista de Atendimentos"));

		return ResponseEntity.ok(atendimentoDto);
	}

	@PutMapping("{id}")
	public ResponseEntity<AtendimentoDto> alterarAtendimento(@RequestBody @Valid AtendimentoDto dto,
			@PathVariable Long id) {
		ResponseEntity<Atendimento> atendimento = atendimentoService.alterarAtendimento(id,
				AtendimentoMapper.fromDto(dto));

		return ResponseEntity.ok(AtendimentoMapper.consultaAtendimento(atendimento.getBody()));
	}

	@DeleteMapping("{id}")
	public ResponseEntity<String> excluirAtendimento(@PathVariable Long id) {
		return atendimentoService.excluirAtendimento(id);
	}

}
