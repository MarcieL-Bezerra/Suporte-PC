package br.com.marciel.sosnet.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.marciel.sosnet.model.Usuario;
@Repository
public interface UserRepository extends JpaRepository<Usuario, Long> {
	Page<Usuario> findAll(Pageable pageable);
	
	Optional<Usuario> findByCpf(String cpf);

}
