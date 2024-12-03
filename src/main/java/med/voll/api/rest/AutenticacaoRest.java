package med.voll.api.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import med.voll.api.dtos.DadosAutenticacaoDTO;
import med.voll.api.dtos.DadosTokenJWT;
import med.voll.api.service.TokenService;
import med.voll.api.vo.UsuarioVO;

@RestController
@RequestMapping("/login")
public class AutenticacaoRest {
	
	@Autowired
	private AuthenticationManager manager;
	
	@Autowired
	private TokenService tokenService;
	
	
	@PostMapping
	public ResponseEntity efetuarLogin(@RequestBody @Valid DadosAutenticacaoDTO dados) {
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());
		
		Authentication authentication = manager.authenticate(token); //chama loadUserByUsername do AutenticacaoService
		                                                             //devolve uma instância do UsuarioVO porém convertido
		
		String tokenJWT = tokenService.gerarToken((UsuarioVO) authentication.getPrincipal());
		
		return ResponseEntity.ok(new DadosTokenJWT(tokenJWT)); //retorna um 200 e no corpo um token com validade de 2hrs
	}

}
