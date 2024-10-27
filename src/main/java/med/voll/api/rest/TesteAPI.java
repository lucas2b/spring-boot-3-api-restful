package med.voll.api.rest;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import med.voll.api.dtos.CadastroMedicoDTO;
import med.voll.api.dtos.CadastroPacienteDTO;
import med.voll.api.dtos.EnderecoDTO;
import med.voll.api.dtos.Especialidade;
import med.voll.api.service.MedicoService;
import med.voll.api.service.PacienteService;
import med.voll.api.vo.MedicoVO;

@RestController
@RequestMapping("/testes")
public class TesteAPI {
	
	@Autowired
	PacienteService pacienteService;
	
	@Autowired
	MedicoService medicoService;
	
	
	@GetMapping(path = "/1")
	public void teste1() {
		
		CadastroMedicoDTO cadastroMedicoDTO = new CadastroMedicoDTO("Xapatin",
				"xapatin@gmail.com",
				"123456",
				Especialidade.GINECOLOGIA,
				new EnderecoDTO("Rua", "centro", "96015280", "Pelotas", "RS", null, "123"),
				"54981232432");
		MedicoVO medicoCadastrado =  medicoService.cadastrar(cadastroMedicoDTO);
		
		CadastroPacienteDTO cadastroPacienteDTO = new CadastroPacienteDTO("Lucas", "lucasbonine@live.com", medicoCadastrado);
		pacienteService.cadastrar(cadastroPacienteDTO);
	}
	
	@GetMapping(path = "/2/{id}")
	public void teste2(@PathVariable Long id) {
	    Optional<MedicoVO> medicoRecuperadoBD = medicoService.buscarMedicoPorId(id);
	    System.out.println(medicoRecuperadoBD.get().toString());
	}

}
