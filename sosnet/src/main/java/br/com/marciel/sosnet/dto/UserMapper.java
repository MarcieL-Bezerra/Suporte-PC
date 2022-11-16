package br.com.marciel.sosnet.dto;

import br.com.marciel.sosnet.model.Usuario;

public class UserMapper {

	public static UserDto consultaUser(Usuario usuario) {
		UserDto userDtoRecebido = new UserDto(usuario.getId(), usuario.getCpf(), usuario.getNome(), usuario.getSenha(),
				usuario.getType());
		userDtoRecebido.setSenha("**##***##*");
		return userDtoRecebido;
	}

	public static Usuario fromDto(UserDto dto) {
		return new Usuario(null, dto.getCpf(), dto.getNome(), dto.getSenha(), dto.getType());

	}
}
