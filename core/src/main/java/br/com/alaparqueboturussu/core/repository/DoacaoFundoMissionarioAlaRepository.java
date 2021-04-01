package br.com.alaparqueboturussu.core.repository;

import br.com.alaparqueboturussu.core.entity.DoacaoFundoMissionarioAla;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoacaoFundoMissionarioAlaRepository extends JpaRepository<DoacaoFundoMissionarioAla, Long> {
}
