package br.com.marciel.sosnet.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import br.com.marciel.sosnet.model.utiu.UserType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_usuario")
public class Usuario{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank(message = "Favor verificar o campo CPF")
	private String cpf;
	@NotBlank(message = "Favor verificar o campo CPF")
	private String nome;
	@NotBlank(message = "Favor verificar o campo CPF")
	private String senha;

	private UserType type = UserType.USER;

}
