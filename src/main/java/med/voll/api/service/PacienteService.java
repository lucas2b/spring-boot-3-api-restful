package med.voll.api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import med.voll.api.dtos.CadastroPacienteDTO;
import med.voll.api.repository.PacienteRepository;
import med.voll.api.vo.PacienteVO;

@Service
public class PacienteService {
	
	@Autowired
	private PacienteRepository pacienteRepository;
	
	@Transactional
	public void cadastrar(CadastroPacienteDTO dados) {
		pacienteRepository.save(new PacienteVO(dados));
	}
	
	public Optional<PacienteVO> buscarPorId(Long id) {
		return pacienteRepository.findById(id);
	}

}
