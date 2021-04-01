package br.com.alaparqueboturussu.core.repository;

import br.com.alaparqueboturussu.core.entity.Membro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MembroRepository extends JpaRepository<Membro, Long> {
    Membro findByNumero(String numero);

    Membro findByNomeAndSobrenome(String nome, String sobrenome);

    List<Membro> findByAtivo(boolean ativo);
}
