package med.voll.api.dtos;

import med.voll.api.enums.Especialidade;
import med.voll.api.vo.MedicoVO;

public record DadosListagemMedico(Long id, String nome, String email, String crm, Especialidade especialidade) {

	//Para alguns DTOS que recebem outro tipo de objeto no construtor, é necessário criar um construtor personalizado
	public DadosListagemMedico(MedicoVO medicoVO) {
		this(medicoVO.getId(), medicoVO.getNome(), medicoVO.getEmail(), medicoVO.getCrm(), medicoVO.getEspecialidade()); //chama o construtor do record
	}
}
