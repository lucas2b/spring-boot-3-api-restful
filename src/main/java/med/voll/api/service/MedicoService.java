package med.voll.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
	
	@Transactional
	public MedicoVO atualizar(MedicoVO medicoVO) {
		return medicoRepository.save(medicoVO);
	}
	
	//sem paginação
	public List<DadosListagemMedico> listarTodos(){
		return medicoRepository.findAll().stream().map(DadosListagemMedico::new).toList();
	}
	
	public Page<DadosListagemMedico> listarTodosPaginado(Pageable paginacao){
		return medicoRepository.findAll(paginacao).map(DadosListagemMedico::new);
	}
	
	public Optional<MedicoVO> buscarMedicoPorId(Long id){
		return medicoRepository.findById(id);
	}

}
