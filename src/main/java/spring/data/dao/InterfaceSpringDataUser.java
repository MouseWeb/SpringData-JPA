package spring.data.dao;

import java.util.List;

import javax.persistence.LockModeType;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import spring.data.model.UsuarioSpringData;

@Repository
public interface InterfaceSpringDataUser 
extends CrudRepository<UsuarioSpringData, Long> {
	
	@Async
	@Transactional(readOnly = true)
	@Query(value = "select p from UsuarioSpringData p where p.nome like %?1%")
	public List<UsuarioSpringData> buscaPorNomeAsync (String nome);
	
	
	@Transactional(readOnly = true)
	@Query(value = "select p from UsuarioSpringData p where p.nome like %?1%")
	public List<UsuarioSpringData> buscaPorNome (String nome);
	

	@Transactional(readOnly = true)
	@Query(value = "select p from UsuarioSpringData p where p.nome like %?1%")
	public List<UsuarioSpringData> buscaPorNomeSort (String nome, Sort sort);
	
	
	@Lock(LockModeType.READ)
	@Transactional(readOnly = true)
	@Query(value = "select p from UsuarioSpringData p where p.nome = :paramnome")
	public UsuarioSpringData buscaPorNomeParam (@Param("paramnome") String paramnome);
	
	
	default <S extends UsuarioSpringData> S saveAtual(S entity) {
		 // Processa outros métodos e regar antes de salvar
		return save(entity);
	}
	
	@Modifying
	@Transactional
	@Query("delete from UsuarioSpringData u where u.nome = ?1")
	public void deletePorNome(String nome);
	
	@Modifying
	@Transactional
	@Query("update UsuarioSpringData u set u.email = ?1 where u.nome = ?2")
	public void updateEmailPorNome(String email, String nome);
}
