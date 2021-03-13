package br.com.alaparqueboturussu.repository;

import br.com.alaboturussu.core.entity.Membro;
import br.com.alaparqueboturussu.dto.SituacaoDoador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SituacaoDoadorRepository extends JpaRepository<Membro, Long> {

    @Query("SELECT")
    List<SituacaoDoador> buscarSituacaoDoadores();
}
