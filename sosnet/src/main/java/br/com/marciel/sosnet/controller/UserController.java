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

import br.com.marciel.sosnet.dto.UserDto;
import br.com.marciel.sosnet.dto.UserMapper;
import br.com.marciel.sosnet.model.Usuario;
import br.com.marciel.sosnet.service.UserService;

@RestController
@RequestMapping("/usuarios")
public class UserController {
	@Autowired
	UserService userService;

	@GetMapping
	public ResponseEntity<Page<UserDto>> buscarTodosOsUsuarios(@PageableDefault Pageable pageable) {
		Page<UserDto> userRecebidos = userService.listarTodosOsUsuarios(pageable).map(UserMapper::consultaUser);
		for (UserDto userRecebido : userRecebidos) {
			String cpf = userRecebido.getCpf();
			userRecebido.add(linkTo(methodOn(UserController.class).buscarUsuarioCPFouId(pageable, cpf)).withSelfRel());
		}

		return ResponseEntity.ok(userRecebidos);
	}

	@PostMapping
	public ResponseEntity<UserDto> salvarUsuario(@RequestBody @Valid UserDto dto) {
		Usuario usuario = userService.salvarUsuario(UserMapper.fromDto(dto));

		return ResponseEntity.ok(UserMapper.consultaUser(usuario));
	}

	@GetMapping("{idCpf}")
	public ResponseEntity<UserDto> buscarUsuarioCPFouId(@PageableDefault Pageable pageable,
			@PathVariable(value = "idCpf") String cpf) {
		Usuario usuario = userService.buscarUsuario(cpf);
		UserDto userDto = UserMapper.consultaUser(usuario);
		userDto.add(
				linkTo(methodOn(UserController.class).buscarTodosOsUsuarios(pageable)).withRel("Lista de Usuários"));
		return ResponseEntity.ok(userDto);
	}

	@PutMapping("{id}")
	public ResponseEntity<UserDto> atualizarUsuario(@RequestBody @Valid UserDto dto, @PathVariable Long id) {
		ResponseEntity<Usuario> usuario = userService.alterarUsuario(id, UserMapper.fromDto(dto));

		return ResponseEntity.ok(UserMapper.consultaUser(usuario.getBody()));
	}

	@DeleteMapping("{id}")
	public ResponseEntity<String> excluirUsuario(@PathVariable Long id) {
		return userService.excluirUsuario(id);
	}
}
