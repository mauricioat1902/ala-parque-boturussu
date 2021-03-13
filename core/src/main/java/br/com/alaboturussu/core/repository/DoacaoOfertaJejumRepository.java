package br.com.alaboturussu.core.repository;

import br.com.alaboturussu.core.entity.DoacaoOfertaJejum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoacaoOfertaJejumRepository extends JpaRepository<DoacaoOfertaJejum, Long> {
}
