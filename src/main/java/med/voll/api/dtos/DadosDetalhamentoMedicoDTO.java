package med.voll.api.dtos;

import med.voll.api.enums.Especialidade;
import med.voll.api.vo.MedicoVO;

public record DadosDetalhamentoMedicoDTO(Long id, String nome, String email, String crm, String telefone, Especialidade especialidade, DadosDetalhamentoEnderecoDTO endereco) {

	//Para alguns DTOS que recebem outro tipo de objeto no contrutor, é necessário criar um construtor personalizado
	public DadosDetalhamentoMedicoDTO(MedicoVO medicoVO) {
		this(medicoVO.getId(), medicoVO.getNome(), medicoVO.getEmail(), medicoVO.getCrm(), medicoVO.getTelefone(),
				medicoVO.getEspecialidade(), new DadosDetalhamentoEnderecoDTO(medicoVO.getEndereco()));
	}
	
}
