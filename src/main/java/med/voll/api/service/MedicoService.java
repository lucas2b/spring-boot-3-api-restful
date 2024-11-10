package med.voll.api.service;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import med.voll.api.dtos.AtualizacaoMedicoDTO;
import med.voll.api.dtos.CadastroMedicoDTO;
import med.voll.api.dtos.DadosDetalhamentoMedicoDTO;
import med.voll.api.dtos.DadosListagemMedico;
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
		return  medicoRepository.findAll(paginacao).map(DadosListagemMedico::new);
	}
	
	public MedicoVO buscarMedicoPorId(Long id){
		return medicoRepository.getReferenceById(id);
	}
	
	@Transactional
	public DadosDetalhamentoMedicoDTO atualizar(AtualizacaoMedicoDTO dados)  {
		MedicoVO medico = this.buscarMedicoPorId(dados.id());
		if(Objects.nonNull(medico)) {
			medico.setNome(dados.nome());
			medico.setEmail(dados.telefone());
			medico.setEndereco(new EnderecoVO(dados.endereco()));
			
			//essa chamada é dispensável, pois a JPA detecta a mudança nos atributos do objeto e faz o update automático
			//medicoRepository.save(medico.get()); 
		}
		return new DadosDetalhamentoMedicoDTO(medico);
	}
	
	@Transactional
	public void excluir(Long id) {
		medicoRepository.deleteById(id);
	}

}
