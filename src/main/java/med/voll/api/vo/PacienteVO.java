package med.voll.api.vo;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import med.voll.api.dtos.CadastroPacienteDTO;

@Entity
@Table(name = "pacientes")
public class PacienteVO {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String email;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "medico_id") //definição do nome desejado para criar a coluna em Pacientes que vai fazer o join
	private MedicoVO medico;
	
	public PacienteVO(CadastroPacienteDTO pacienteDTO) {
		this.nome = pacienteDTO.nome();
		this.email = pacienteDTO.email();
		this.medico = pacienteDTO.medico();
	}
	
	public PacienteVO() {
		
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

	public MedicoVO getMedico() {
		return medico;
	}

	public void setMedico(MedicoVO medico) {
		this.medico = medico;
	}

	@Override
	public String toString() {
		return "PacienteVO [id=" + id + ", nome=" + nome + ", email=" + email +"]";
	}
	
	

}
