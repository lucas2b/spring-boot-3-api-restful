package med.voll.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import jakarta.transaction.Transactional;
import med.voll.api.dtos.CadastroMedicoDTO;
import med.voll.api.model.MedicoEntity;
import med.voll.api.repository.MedicoRepository;

@Service
public class MedicoService {
	
	@Autowired
	private MedicoRepository medicoRepository;
	
	@Transactional
	public void cadastrar(CadastroMedicoDTO dados) {
		medicoRepository.save(new MedicoEntity(dados));
	}

}
