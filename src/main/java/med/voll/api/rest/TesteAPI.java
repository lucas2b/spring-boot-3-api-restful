package med.voll.api.rest;

import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import med.voll.api.dtos.CadastroPacienteDTO;
import med.voll.api.service.MedicoService;
import med.voll.api.service.PacienteService;
import med.voll.api.vo.MedicoVO;
import med.voll.api.vo.PacienteVO;

@RestController
@RequestMapping("/testes")
public class TesteAPI {
	
	@Autowired
	PacienteService pacienteService;
	
	@Autowired
	MedicoService medicoService;
	
	/* Demonstração de Cadastro de Paciente utilizando a lista aninhada de médicos 
	 * ou cadastro de paciente individualmente na base de dados*/
	@GetMapping(path = "/1")
	public void teste1() {
		MedicoVO medicoVO = medicoService.buscarMedicoPorId(3L);

		if (Objects.nonNull(medicoVO)) {
			
			CadastroPacienteDTO cadastroPaciente1DTO = new CadastroPacienteDTO("Lucas","lucasbonine@live.com",medicoVO);
			CadastroPacienteDTO cadastroPaciente2DTO = new CadastroPacienteDTO("Maiara","maiaratorchelsen@gmail.com",medicoVO);
			CadastroPacienteDTO cadastroPaciente3DTO = new CadastroPacienteDTO("Alexandre","alexandrebonine@hotmail.com",medicoVO);

			// Insert na entidade direta de PacienteVO, um por um.
			/*
			 * Essa maneira não permite manipular a lista de pacientes do VO do médico, apenas salva-os na base
			 */
//			pacienteService.cadastrar(cadastroPaciente1DTO);
//			pacienteService.cadastrar(cadastroPaciente2DTO);
//			pacienteService.cadastrar(cadastroPaciente3DTO);

			// Insert na lista de pacientes da entidade MedicoVO.
			/*
			 * Essa maneira proporciona manipular o médico na contiuidade do código já acessando sua lista de pacientes
			 */
			medicoVO.adicionarPaciente(new PacienteVO(cadastroPaciente1DTO));
			medicoVO.adicionarPaciente(new PacienteVO(cadastroPaciente2DTO));
			medicoVO.adicionarPaciente(new PacienteVO(cadastroPaciente3DTO));
			medicoService.atualizar(medicoVO);
			
			medicoVO.getListaPacientes().get(0).setNome("NOME ALTERADO"); //manipulação de paciente direto na lista
			medicoService.atualizar(medicoVO);
		}

	}
	
	//Trazer VO de médico com lista de pacientes aninhado
	@GetMapping(path = "/2")
	public void teste2(@RequestParam(required = false) Long id) {
	    if (id != null) {
	        MedicoVO medicoRecuperadoBD = medicoService.buscarMedicoPorId(id);
	        
	        if (Objects.nonNull(medicoRecuperadoBD)) {
	            System.out.println("Lista de pacientes do médico " + medicoRecuperadoBD.getNome());
	            medicoRecuperadoBD.getListaPacientes().forEach(p -> System.out.println(p.toString()));
	        } else {
	            System.out.println("Médico com o ID fornecido não foi encontrado.");
	        }
	    } else {
	        System.out.println("ID do médico não fornecido.");
	    }
	}

}
