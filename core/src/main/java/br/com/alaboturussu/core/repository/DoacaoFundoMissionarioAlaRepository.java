package br.com.alaboturussu.core.repository;

import br.com.alaboturussu.core.entity.DoacaoFundoMissionarioAla;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoacaoFundoMissionarioAlaRepository extends JpaRepository<DoacaoFundoMissionarioAla, Long> {
}
