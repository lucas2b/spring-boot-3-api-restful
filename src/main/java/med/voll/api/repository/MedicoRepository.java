package med.voll.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import med.voll.api.model.MedicoEntity;

public interface MedicoRepository extends JpaRepository<MedicoEntity, Long> {
	
	

}
