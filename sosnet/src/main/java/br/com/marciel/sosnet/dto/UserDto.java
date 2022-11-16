package br.com.marciel.sosnet.dto;

import org.springframework.hateoas.RepresentationModel;

import br.com.marciel.sosnet.model.utiu.UserType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto extends RepresentationModel<UserDto>  {
	private Long id;
	private String cpf;
	private String nome;
	private String senha;
	private UserType type = UserType.USER;
}
