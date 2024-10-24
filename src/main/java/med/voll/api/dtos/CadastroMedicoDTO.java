package med.voll.api.dtos;

public record CadastroMedicoDTO(String nome, String email, String crm, Especialidade especialidade,
		EnderecoDTO endereco) {

}
