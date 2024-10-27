package med.voll.api.dtos;

import med.voll.api.vo.MedicoVO;

public record DadosListagemMedico(String nome, String email, String crm, Especialidade especialidade) {

	public DadosListagemMedico(MedicoVO medicoEntity) {
		this(medicoEntity.getNome(), medicoEntity.getEmail(), medicoEntity.getCrm(), medicoEntity.getEspecialidade()); //chama o construtor do record
	}
}
