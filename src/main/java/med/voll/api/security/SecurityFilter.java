package med.voll.api.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import med.voll.api.repository.UsuarioRepository;
import med.voll.api.service.TokenService;
import med.voll.api.vo.UsuarioVO;

@Component
public class SecurityFilter extends OncePerRequestFilter {
	
	
	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String tokenRecuperado = recuperarTokenDaRequisicao(request);
		
		if (tokenRecuperado != null) {
			String subject = tokenService.validarTokenERecuperarTokenSubject(tokenRecuperado);
			UserDetails usuario = usuarioRepository.findByLogin(subject);

			UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(usuario, null,
					usuario.getAuthorities());

			SecurityContextHolder.getContext().setAuthentication(authentication); //libera na regra da classe SecurityConfigurations
		}
		
		
		filterChain.doFilter(request, response); //dá continuidade à cadeira de fluxo da requisição, não loga o usuário.
	}

	private String recuperarTokenDaRequisicao(HttpServletRequest request) {
		String tokenRecuperado = request.getHeader("Authorization");
		
		//para as chamadas que não são do login, pois login não vem o cabeçalho authorization
		if(tokenRecuperado != null) {
			return tokenRecuperado.replace("Bearer ", "");			
		}
		return null;
	}

}
