package br.com.marciel.sosnet.service;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.marciel.sosnet.model.Atendimento;
import br.com.marciel.sosnet.repository.AtendimentoRepository;

@Service
public class AtendimentoService {
	final AtendimentoRepository atendimentoRepository;

	public AtendimentoService(AtendimentoRepository atendimentoRepository) {
		this.atendimentoRepository = atendimentoRepository;
	}
	
	public Page<Atendimento> listarTodosOsAtendimentos(Pageable pageable) {
		return atendimentoRepository.findAll(pageable);
	}

	public Atendimento salvarAtendimento(Atendimento atendimento) {
		return atendimentoRepository.save(atendimento);
	};

	public Atendimento buscarAtendimento(Long id) {
		Optional<Atendimento> optional = atendimentoRepository.findById(id);

		return optional.orElseThrow(() -> new EntityNotFoundException("Atendimento não encontrado"));
	};

	public ResponseEntity<Atendimento> alterarAtendimento(Long id, Atendimento atendimento) {
		try {
			Atendimento atendimentoOriginal = this.buscarAtendimento(id);
			atendimento.setId(atendimentoOriginal.getId());
			return ResponseEntity.ok(atendimentoRepository.save(atendimento));

		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}

	};

	public ResponseEntity<String> excluirAtendimento(Long id) {
		try {
			Atendimento atendimentoOriginal = this.buscarAtendimento(id);
			atendimentoRepository.delete(atendimentoOriginal);

			return new ResponseEntity<>("Atendimento excluído!", HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>("Atendimento não pode ser excluído!", HttpStatus.BAD_REQUEST);
		}
	};

}
