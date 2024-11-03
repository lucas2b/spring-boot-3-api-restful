package med.voll.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import med.voll.api.dtos.AtualizacaoMedicoDTO;
import med.voll.api.dtos.CadastroMedicoDTO;
import med.voll.api.dtos.DadosListagemMedico;
import med.voll.api.exceptions.ApplicationException;
import med.voll.api.repository.MedicoRepository;
import med.voll.api.vo.EnderecoVO;
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
	
	public Page<DadosListagemMedico> listar(Pageable paginacao){
		return medicoRepository.findAll(paginacao).map(DadosListagemMedico::new);
	}
	
	public Optional<MedicoVO> buscarMedicoPorId(Long id){
		return medicoRepository.findById(id);
	}
	
	@Transactional
	public void atualizar(AtualizacaoMedicoDTO dados) throws ApplicationException {
		Optional<MedicoVO> medico = this.buscarMedicoPorId(dados.id());
		if(medico.isPresent()) {
			medico.get().setNome(dados.nome());
			medico.get().setEmail(dados.telefone());
			medico.get().setEndereco(new EnderecoVO(dados.endereco()));
			
			//essa chamada é dispensável, pois a JPA detecta a mudança nos atributos do objeto e faz o update automático
			//medicoRepository.save(medico.get()); 
		}else {
			throw new ApplicationException("Médico não localizado na base de dados");
		}
	}
	
	@Transactional
	public void excluir(Long id) {
		medicoRepository.deleteById(id);
	}

}
