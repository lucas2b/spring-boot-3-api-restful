package med.voll.api.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import med.voll.api.dtos.AtualizacaoMedicoDTO;
import med.voll.api.dtos.CadastroMedicoDTO;
import med.voll.api.dtos.DadosListagemMedico;
import med.voll.api.exceptions.ApplicationException;
import med.voll.api.service.MedicoService;

@RestController
@RequestMapping("/medicos")
public class MedicoRest {
	
	@Autowired
	private MedicoService medicoService;
	
	@PostMapping
	public void cadastrar(@RequestBody @Valid CadastroMedicoDTO dados) {
		medicoService.cadastrar(dados);
	}
	
	@GetMapping
	public Page<DadosListagemMedico> listar(Pageable paginacao){
		return medicoService.listar(paginacao);
	}
	
	@PutMapping
	public void atualizar(@RequestBody @Valid AtualizacaoMedicoDTO dados) {
		try {
			medicoService.atualizar(dados);
		}catch(ApplicationException e) {
			e.printStackTrace();
		}
	}
}


