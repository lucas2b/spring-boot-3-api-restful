package med.voll.api.vo;

import java.util.List;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import med.voll.api.dtos.CadastroMedicoDTO;
import med.voll.api.dtos.Especialidade;

@Entity(name = "MedicoEntity")
@Table(name = "medicos")
public class MedicoVO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String email;
	private String crm;
	private String telefone;

	@Enumerated
	private Especialidade especialidade;

	@Embedded
	private EnderecoVO endereco; //para organização do código
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "medico", cascade = CascadeType.ALL)
	private List<PacienteVO> listaPacientes;

	public List<PacienteVO> getListaPacientes() {
		return listaPacientes;
	}

	public void setListaPacientes(List<PacienteVO> listaPacientes) {
		this.listaPacientes = listaPacientes;
	}

	public MedicoVO() {

	}

	public MedicoVO(CadastroMedicoDTO dados) {
		this.nome = dados.nome();
		this.email = dados.email();
		this.crm = dados.crm();
		this.especialidade = dados.especialidade();
		this.endereco = new EnderecoVO(dados.endereco());
		this.telefone = dados.telefone();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCrm() {
		return crm;
	}

	public void setCrm(String crm) {
		this.crm = crm;
	}

	public Especialidade getEspecialidade() {
		return especialidade;
	}

	public void setEspecialidade(Especialidade especialidade) {
		this.especialidade = especialidade;
	}

	public EnderecoVO getEndereco() {
		return endereco;
	}

	public void setEndereco(EnderecoVO endereco) {
		this.endereco = endereco;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MedicoVO other = (MedicoVO) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "MedicoVO [id=" + id + ", nome=" + nome + ", email=" + email + ", crm=" + crm + ", telefone=" + telefone
				+ ", especialidade=" + especialidade + ", endereco=" + endereco + ", listaPacientes=" + listaPacientes
				+ "]";
	}
	

	public void adicionarPaciente(PacienteVO paciente) {
		paciente.setMedico(this);
		listaPacientes.add(paciente);
	}
	
	

}
