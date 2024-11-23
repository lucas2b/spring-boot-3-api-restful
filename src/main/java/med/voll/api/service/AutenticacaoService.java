package med.voll.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import med.voll.api.repository.UsuarioRepository;

@Service //anotação que indica ao Spring que são configurações do framework
public class AutenticacaoService implements UserDetailsService{
	
	@Autowired
	private UsuarioRepository usuarioRespository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return usuarioRespository.findByLogin(username);
	}

}
