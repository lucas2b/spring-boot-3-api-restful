package med.voll.api.dtos;

import med.voll.api.vo.EnderecoVO;

public record DadosDetalhamentoEnderecoDTO(String logradouro, String bairro, String cep, String numero, String uf, String complemento, String cidade) {
	
	//Para alguns DTOS que recebem outro tipo de objeto no contrutor, é necessário criar um construtor personalizado
	public DadosDetalhamentoEnderecoDTO(EnderecoVO enderecoVO) {
		this(enderecoVO.getLogradouro(), enderecoVO.getBairro(), enderecoVO.getCep(), enderecoVO.getNumero(),
				enderecoVO.getUf(), enderecoVO.getComplemento(), enderecoVO.getCidade());
	}
}

