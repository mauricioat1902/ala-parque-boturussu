package br.com.alaparqueboturussu.core.service;

import br.com.alaparqueboturussu.core.entity.DoacaoDizimo;
import br.com.alaparqueboturussu.core.interfaces.IDoacaoDizimoService;
import br.com.alaparqueboturussu.core.repository.DoacaoDizimoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class DoacaoDizimoService implements IDoacaoDizimoService {
    @Autowired
    private DoacaoDizimoRepository repository;


    @Override
    public DoacaoDizimo salvar(DoacaoDizimo doacaoDizimo) {
        return repository.save(doacaoDizimo);
    }

    @Override
    public DoacaoDizimo buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<DoacaoDizimo> buscarTodos() {
        return repository.findAll();
    }

    @Override
    public void remover(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<DoacaoDizimo> salvarTodos(List<DoacaoDizimo> doacoesDizimo) {
        return repository.saveAll(doacoesDizimo);
    }

    @Override
    public List<DoacaoDizimo> buscarEntreDatas(LocalDate dataInicial, LocalDate dataFinal) {
        return repository.findByDataBetween(dataInicial, dataFinal);
    }
}
