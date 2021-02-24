package br.com.alaboturussu.repository;

import br.com.alaboturussu.entity.DoacaoDizimo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoacaoDizimoRepository extends JpaRepository<DoacaoDizimo, Long> {
}
