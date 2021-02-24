package br.com.alaboturussu.repository;

import br.com.alaboturussu.entity.DoacaoFundoMissionarioAla;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoacaoFundoMissionarioAlaRepository extends JpaRepository<DoacaoFundoMissionarioAla, Long> {
}
