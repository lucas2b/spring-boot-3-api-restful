package med.voll.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import med.voll.api.vo.UsuarioVO;

public interface UsuarioRepository extends JpaRepository<UsuarioVO, Long>{

	UserDetails findByLogin(String username);//declaração do método que o JPA Data implementa filtrando usuarios pelo Login

}
