package br.com.alaboturussu.core.service;

import br.com.alaboturussu.core.entity.DoacaoCategoriaOutros;
import br.com.alaboturussu.core.interfaces.IDoacaoCategoriaOutrosService;
import br.com.alaboturussu.core.repository.DoacaoCategoriaOutrosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoacaoCategoriaOutrosService implements IDoacaoCategoriaOutrosService {
    @Autowired
    private DoacaoCategoriaOutrosRepository repository;


    @Override
    public DoacaoCategoriaOutros salvar(DoacaoCategoriaOutros doacaoCategoriaOutros) {
        return repository.save(doacaoCategoriaOutros);
    }

    @Override
    public DoacaoCategoriaOutros buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<DoacaoCategoriaOutros> buscarTodos() {
        return repository.findAll();
    }

    @Override
    public void remover(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<DoacaoCategoriaOutros> salvarTodos(List<DoacaoCategoriaOutros> doacoesCategoriaOutros) {
        return repository.saveAll(doacoesCategoriaOutros);
    }
}
