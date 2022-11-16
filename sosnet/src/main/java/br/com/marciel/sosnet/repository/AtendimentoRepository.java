package br.com.marciel.sosnet.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.marciel.sosnet.model.Atendimento;
@Repository
public interface AtendimentoRepository extends JpaRepository<Atendimento, Long> {
	Page<Atendimento> findAll(Pageable pageable);
}
