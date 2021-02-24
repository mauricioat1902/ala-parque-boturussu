package br.com.alaboturussu.repository;

import br.com.alaboturussu.entity.DoacaoCategoriaOutros;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoacaoCategoriaOutrosRepository extends JpaRepository<DoacaoCategoriaOutros, Long> {
}
