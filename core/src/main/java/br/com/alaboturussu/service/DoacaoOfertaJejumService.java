package br.com.alaboturussu.service;

import br.com.alaboturussu.entity.DoacaoOfertaJejum;
import br.com.alaboturussu.interfaces.IDoacaoOfertaJejumService;
import br.com.alaboturussu.repository.DoacaoOfertaJejumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoacaoOfertaJejumService implements IDoacaoOfertaJejumService {
    @Autowired
    private DoacaoOfertaJejumRepository repository;


    @Override
    public DoacaoOfertaJejum salvar(DoacaoOfertaJejum doacaoOfertaJejum) {
        return repository.save(doacaoOfertaJejum);
    }

    @Override
    public DoacaoOfertaJejum buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<DoacaoOfertaJejum> buscarTodos() {
        return repository.findAll();
    }

    @Override
    public void remover(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<DoacaoOfertaJejum> salvarTodos(List<DoacaoOfertaJejum> doacoesOfertaJejum) {
        return repository.saveAll(doacoesOfertaJejum);
    }
}
