package med.voll.api.rest;

import java.net.URI;
import java.util.Optional;

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
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.validation.Valid;
import med.voll.api.dtos.AtualizacaoMedicoDTO;
import med.voll.api.dtos.CadastroMedicoDTO;
import med.voll.api.dtos.DadosDetalhamentoMedicoDTO;
import med.voll.api.dtos.DadosListagemMedico;
import med.voll.api.service.MedicoService;
import med.voll.api.vo.MedicoVO;

@RestController
@RequestMapping("/medicos")
public class MedicoRest {
	
	@Autowired
	private MedicoService medicoService;
	
	/*
	 * O post precisa devolver um código 201 (Created), 
	 * o Body com o objeto criado e 
	 * uma URI com o endereço do recurso criado no header
	 */
	@PostMapping
	public ResponseEntity cadastrar(@RequestBody @Valid CadastroMedicoDTO dados, UriComponentsBuilder uriBuilder) {
		MedicoVO medicoCriado = medicoService.cadastrar(dados);
		URI uri = uriBuilder.path("/medicos/{id}").buildAndExpand(medicoCriado.getId()).toUri();
		return ResponseEntity.created(uri).body(new DadosDetalhamentoMedicoDTO(medicoCriado)); //201
	}
	
	@GetMapping
	public ResponseEntity<Page<DadosListagemMedico>> listar(Pageable paginacao){
		return ResponseEntity.ok(medicoService.listar(paginacao)); //200
	}
	
	@PutMapping
	public ResponseEntity<DadosDetalhamentoMedicoDTO> atualizar(@RequestBody @Valid AtualizacaoMedicoDTO dados) {		
		return ResponseEntity.ok(medicoService.atualizar(dados)); //200
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity excluir(@PathVariable Long id) {
		medicoService.excluir(id);
		return ResponseEntity.noContent().build(); //204
	}
	

	@GetMapping("/{id}")
	public ResponseEntity detalhar(@PathVariable Long id)  {		
		return ResponseEntity.ok(new DadosDetalhamentoMedicoDTO(medicoService.buscarMedicoPorId(id))); //200
	}
}


