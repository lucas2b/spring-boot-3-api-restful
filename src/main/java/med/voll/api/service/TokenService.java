package med.voll.api.service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;

import med.voll.api.vo.UsuarioVO;

@Service //anotação que indica ao Spring que são configurações do framework
public class TokenService {
	
	@Value("${api.security.token.secret}")
	private String secret;
	
	public String gerarToken(UsuarioVO usuario) {
		
		System.out.println("SEGREDO: " + secret);

		try {
		    Algorithm algoritmoEncriptacao = Algorithm.HMAC256(secret);
		    
		    return JWT.create()
		        .withIssuer("API Voll.med")
		        .withSubject(usuario.getLogin()) //guarda o proprietário do token
		        .withExpiresAt(dataExpiracao()) //define o tempo de expiração
		        .sign(algoritmoEncriptacao);
		} catch (JWTCreationException exception){
			throw new RuntimeException("Erro ao gerar token JWT", exception); //RuntimeException não força os chamadores a tratarem essa Exception
		}
	}
	
	private Instant dataExpiracao() {
		return LocalDateTime.now().plusHours(2L).toInstant(ZoneOffset.of("-03:00"));
	}

}
