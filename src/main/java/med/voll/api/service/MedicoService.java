package med.voll.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import med.voll.api.dtos.CadastroMedicoDTO;
import med.voll.api.dtos.DadosListagemMedico;
import med.voll.api.repository.MedicoRepository;
import med.voll.api.vo.MedicoVO;

@Service
public class MedicoService {
	
	@Autowired
	private MedicoRepository medicoRepository;
	
	@Transactional
	public MedicoVO cadastrar(CadastroMedicoDTO dados) {
		return medicoRepository.save(new MedicoVO(dados));
	}
	
	public List<DadosListagemMedico> listarTodos(){
		return medicoRepository.findAll().stream().map(DadosListagemMedico::new).toList();
	}
	
	public Optional<MedicoVO> buscarMedicoPorId(Long id){
		return medicoRepository.findById(id);
	}

}
