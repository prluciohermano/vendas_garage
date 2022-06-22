package io.github.prluciohermano.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import io.github.prluciohermano.domain.entity.Pessoa;

@Repository
public interface Pessoas extends JpaRepository<Pessoa, Long> {
	
	 @Query(value = " select * from Pessoa p where p.nome like '%:nome%' ", nativeQuery = true)
	    List<Pessoa> encontrarPorNome( @Param("nome") Pessoa filtro );

	    @Query(" delete from Pessoa p where p.nome =:nome ")
	    @Modifying
	    void deleteByNome(String nome);
	    boolean existsByNome(String nome);

	    @Query(" select p from Pessoa p left join fetch p.pedidos where p.id = :id  ")
	    Pessoa findPessoaFetchPedidos( @Param("id") Long id );
	    
	    @Query(value = "select p from Pessoa p where upper(trim(p.nome)) like %?1%")
		List<Pessoa> buscarPorNome(String nome);

		
    

}
