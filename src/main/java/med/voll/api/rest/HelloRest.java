package med.voll.api.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import med.voll.api.dtos.Especialidade;

@RestController
@RequestMapping("/hello")
public class HelloRest {
	
	@GetMapping
	public String hello() {
		
		Especialidade instanciaEnum =  Especialidade.buscaEspecialidadePorSetor(1);
		
		return "Hello world! lucas";
	}

}
