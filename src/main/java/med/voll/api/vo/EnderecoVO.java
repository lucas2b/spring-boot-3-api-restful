package med.voll.api.vo;

import jakarta.persistence.Embeddable;
import med.voll.api.dtos.EnderecoDTO;

@Embeddable
public class EnderecoVO {

	private String logradouro;
	private String bairro;
	private String cep;
	private String numero;
	private String uf;
	private String complemento;
	private String cidade;

	public EnderecoVO(EnderecoDTO enderecoDTO) {
		this.logradouro = enderecoDTO.logradouro();
		this.bairro = enderecoDTO.bairro();
		this.cep = enderecoDTO.cep();
		this.numero = enderecoDTO.numero();
		this.uf = enderecoDTO.uf();
		this.complemento = enderecoDTO.complemento();
		this.cidade = enderecoDTO.cidade();
	}

	public String getLogradouro() {
		return logradouro;
	}

	public EnderecoVO() {
		super();
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	@Override
	public String toString() {
		return "EnderecoVO [logradouro=" + logradouro + ", bairro=" + bairro + ", cep=" + cep + ", numero=" + numero
				+ ", uf=" + uf + ", complemento=" + complemento + ", cidade=" + cidade + "]";
	}
	
	

}
