package med.voll.api.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import med.voll.api.dtos.CadastroMedicoDTO;
import med.voll.api.model.EnderecoEntity;
import med.voll.api.model.MedicoEntity;
import med.voll.api.repository.MedicoRepository;

@RestController
@RequestMapping("/medicos")
public class MedicoRest {
	
	@Autowired
	private MedicoRepository medicoRepository;
	
	@PostMapping
	public void cadastrar(@RequestBody CadastroMedicoDTO dados) {
		System.out.println(dados);
		medicoRepository.save(new MedicoEntity(dados));
		
	}
	
	

}
