package med.voll.api.dtos;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AtualizacaoMedicoDTO(
		
		@NotNull
		Long id,
		
		@NotBlank
		String nome,
		
		@NotBlank
		String telefone,
		
		@NotNull
		@Valid
		EnderecoDTO endereco) {

}
