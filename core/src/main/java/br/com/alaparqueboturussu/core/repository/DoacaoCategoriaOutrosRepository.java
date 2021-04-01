package br.com.alaparqueboturussu.core.repository;

import br.com.alaparqueboturussu.core.entity.DoacaoCategoriaOutros;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoacaoCategoriaOutrosRepository extends JpaRepository<DoacaoCategoriaOutros, Long> {
}
