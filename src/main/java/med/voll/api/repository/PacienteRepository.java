package med.voll.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import med.voll.api.vo.PacienteVO;

public interface PacienteRepository extends JpaRepository<PacienteVO, Long>{

}
