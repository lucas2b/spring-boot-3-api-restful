package med.voll.api.service;

import java.util.List;

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
	public void cadastrar(CadastroMedicoDTO dados) {
		medicoRepository.save(new MedicoVO(dados));
	}
	
	public List<DadosListagemMedico> listarTodos(){
		//return medicoRepository.findAll().stream().map(DadosListagemMedico::new).toList();
		return medicoRepository.findAll().stream().map(DadosListagemMedico::new).toList();
	}

}
