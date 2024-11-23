package med.voll.api.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration //anotação que vale para toda a aplicação
@EnableWebSecurity
public class SecurityConfigurations {
	
	/*
	 * Método que efetivamente remove o processo de segurança
	 * do spring que utilizava autenticação Statefull e fornecia
	 * uma tela de login no navegador na raíz da aplicação.
	 * Com esse método a API está liberada de autenticação.
	 */
	@Bean //a anotação bean serve para exportar uma configuração do Spring para que o framework a utilize
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	    return http.csrf(csrf -> csrf.disable())
	            .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
	            .build();
	}
	
	
	@Bean //a anotação bean serve para exportar uma configuração do Spring para que o framework a utilize
	public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception{
		return configuration.getAuthenticationManager();
		
	}
	
	@Bean //Configura o spring a utilizar a hash de senha BCrypt
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	

}
