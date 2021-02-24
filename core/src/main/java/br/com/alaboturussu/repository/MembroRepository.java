package br.com.alaboturussu.repository;

import br.com.alaboturussu.entity.Membro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MembroRepository extends JpaRepository<Membro, Long> {
    Membro findByNumero(String numero);

    Membro findByNomeAndSobrenome(String nome, String sobrenome);
}
