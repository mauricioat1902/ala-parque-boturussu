package br.com.alaparqueboturussu.core.service;

import br.com.alaparqueboturussu.core.entity.Membro;
import br.com.alaparqueboturussu.core.interfaces.IMembroService;
import br.com.alaparqueboturussu.core.repository.MembroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MembroService implements IMembroService {

    @Autowired
    private MembroRepository repository;

    @Override
    public Membro salvar(Membro membro) {
        return repository.save(membro);
    }

    @Override
    public Membro buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<Membro> buscarTodos() {
        return repository.findAll();
    }

    @Override
    public void remover(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<Membro> salvarTodos(List<Membro> membros) {
        return repository.saveAll(membros);
    }

    @Override
    public Membro buscarPorNomeESobrenome(String nome, String sobrenome) {
        return repository.findByNomeAndSobrenome(nome, sobrenome);
    }

    @Override
    public Membro buscarPorNumero(String numero) {
        return repository.findByNumero(numero);
    }

    @Override
    public List<Membro> buscarPorAtivo(boolean ativo) {
        return repository.findByAtivo(ativo);
    }

}
