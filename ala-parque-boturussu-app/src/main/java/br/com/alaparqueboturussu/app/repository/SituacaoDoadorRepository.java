package br.com.alaparqueboturussu.app.repository;

import br.com.alaparqueboturussu.core.entity.Membro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SituacaoDoadorRepository extends JpaRepository<Membro, Long> {

//    @Query("SELECT")
//    List<SituacaoDoador> buscarSituacaoDoadores();
}
