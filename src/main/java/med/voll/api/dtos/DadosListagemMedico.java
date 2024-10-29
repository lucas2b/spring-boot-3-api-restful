package med.voll.api.dtos;

import med.voll.api.vo.MedicoVO;

public record DadosListagemMedico(Long id, String nome, String email, String crm, Especialidade especialidade) {

	//construtor do record
	public DadosListagemMedico(MedicoVO medicoVO) {
		this(medicoVO.getId(), medicoVO.getNome(), medicoVO.getEmail(), medicoVO.getCrm(), medicoVO.getEspecialidade()); //chama o construtor do record
	}
}
