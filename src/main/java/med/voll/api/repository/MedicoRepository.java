package med.voll.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import med.voll.api.vo.MedicoVO;

public interface MedicoRepository extends JpaRepository<MedicoVO, Long> {
	
	

}
