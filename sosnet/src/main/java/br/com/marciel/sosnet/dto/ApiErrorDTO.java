package br.com.marciel.sosnet.dto;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApiErrorDTO {
	private String message;
	private List<String> errors;
	private HttpStatus status;

	public ApiErrorDTO(String message, String error, HttpStatus status) {
		this.message = message;
		this.errors = Arrays.asList(error);
		this.status = status;
	}

}
