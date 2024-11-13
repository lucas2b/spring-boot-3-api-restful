package med.voll.api.dtos;

import jakarta.validation.constraints.NotBlank;

public record DadosAutenticacaoDTO(
		
		@NotBlank
		String login, 
		
		@NotBlank
		String senha
		
		) {

}
