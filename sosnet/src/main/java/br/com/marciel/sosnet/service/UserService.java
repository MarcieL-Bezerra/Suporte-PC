package br.com.marciel.sosnet.service;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.marciel.sosnet.model.Usuario;
import br.com.marciel.sosnet.repository.UserRepository;

@Service
public class UserService {

	final UserRepository userRepository;

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public Page<Usuario> listarTodosOsUsuarios(Pageable pageable) {
		return userRepository.findAll(pageable);
	}

	public Usuario salvarUsuario(Usuario usuario) {
		return userRepository.save(usuario);
	};

	public Usuario buscarUsuario(String id) {
		if (id.length() == 11) {
			return buscarUsuarioPorCPF(id);
		}
		return buscarUsuarioPorId(Long.parseLong(id));

	};

	public Usuario buscarUsuarioPorId(Long id) {
		Optional<Usuario> optional = userRepository.findById(id);

		return optional.orElseThrow(() -> new EntityNotFoundException("Usuario não encontrado"));
	};

	public Usuario buscarUsuarioPorCPF(String id) {
		Optional<Usuario> optional = userRepository.findByCpf(id);

		return optional.orElseThrow(() -> new EntityNotFoundException("Usuario não encontrado"));
	};

	public ResponseEntity<Usuario> alterarUsuario(Long id, Usuario usuario) {
		try {
			Usuario userOriginal = this.buscarUsuarioPorId(id);
			usuario.setId(userOriginal.getId());
			return ResponseEntity.ok(userRepository.save(usuario));

		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}

	};

	public ResponseEntity<String> excluirUsuario(Long id) {
		try {
			Usuario userOriginal = this.buscarUsuarioPorId(id);
			userRepository.delete(userOriginal);

			return new ResponseEntity<>("Usuario excluído!", HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>("Usuario não pode ser excluído!", HttpStatus.BAD_REQUEST);
		}
	};

}
