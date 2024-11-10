package med.voll.api.exceptions;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class TratadorDeExceptions {
	
	//Tratamento de erro para quando não achou a entidade buscando pelo ID
	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity tratarErro404() {
		return ResponseEntity.notFound().build();
	}
	

	//Tratamento de erro quando alguma validação do bean validation falhou
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity tratarErro400(MethodArgumentNotValidException ex) {
		
		List<FieldError> listaErrosValidacao = ex.getFieldErrors();
		
		return ResponseEntity.badRequest().body(listaErrosValidacao.stream().map(DadosErroValidacao::new).toList());
	}
	
	
	private record DadosErroValidacao(String campo, String msg) {
		public DadosErroValidacao(FieldError erro) {
			this(erro.getField(), erro.getDefaultMessage());
		}
		
	}
}
