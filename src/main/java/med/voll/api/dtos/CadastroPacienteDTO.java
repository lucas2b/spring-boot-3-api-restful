package med.voll.api.dtos;

import med.voll.api.vo.MedicoVO;

public record CadastroPacienteDTO(String nome, String email, MedicoVO medico) {

}
