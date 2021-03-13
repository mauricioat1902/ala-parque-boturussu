package br.com.alaboturussu.core.repository;

import br.com.alaboturussu.core.entity.DoacaoDizimo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface DoacaoDizimoRepository extends JpaRepository<DoacaoDizimo, Long> {
    List<DoacaoDizimo> findByDataBetween(LocalDate dataInicial, LocalDate dataFinal);
}
