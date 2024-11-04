package med.voll.api.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import med.voll.api.dtos.AtualizacaoMedicoDTO;
import med.voll.api.dtos.CadastroMedicoDTO;
import med.voll.api.dtos.DadosDetalhamentoMedicoDTO;
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
	public ResponseEntity<Page<DadosListagemMedico>> listar(Pageable paginacao){
		return ResponseEntity.ok(medicoService.listar(paginacao));
	}
	
	@PutMapping
	public ResponseEntity<DadosDetalhamentoMedicoDTO> atualizar(@RequestBody @Valid AtualizacaoMedicoDTO dados) {		
		try {
			DadosDetalhamentoMedicoDTO retornoAtualizacao = medicoService.atualizar(dados);
			return ResponseEntity.ok(retornoAtualizacao);
		} catch (ApplicationException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		} catch (Exception e) {
			 return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}

	}
	
	@SuppressWarnings("rawtypes")
	@DeleteMapping("/{id}")
	public ResponseEntity excluir(@PathVariable Long id) {
		medicoService.excluir(id);
		return ResponseEntity.noContent().build(); //204
	}
}


