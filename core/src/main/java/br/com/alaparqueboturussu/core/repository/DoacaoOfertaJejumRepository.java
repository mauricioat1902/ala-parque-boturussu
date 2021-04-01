package br.com.alaparqueboturussu.core.repository;

import br.com.alaparqueboturussu.core.entity.DoacaoOfertaJejum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoacaoOfertaJejumRepository extends JpaRepository<DoacaoOfertaJejum, Long> {
}
