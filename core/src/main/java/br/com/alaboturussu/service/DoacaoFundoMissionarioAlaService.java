package br.com.alaboturussu.service;

import br.com.alaboturussu.entity.DoacaoFundoMissionarioAla;
import br.com.alaboturussu.interfaces.IDoacaoFundoMissionarioAlaService;
import br.com.alaboturussu.repository.DoacaoFundoMissionarioAlaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoacaoFundoMissionarioAlaService implements IDoacaoFundoMissionarioAlaService {
    @Autowired
    private DoacaoFundoMissionarioAlaRepository repository;


    @Override
    public DoacaoFundoMissionarioAla salvar(DoacaoFundoMissionarioAla doacaoFundoMissionarioAla) {
        return repository.save(doacaoFundoMissionarioAla);
    }

    @Override
    public DoacaoFundoMissionarioAla buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<DoacaoFundoMissionarioAla> buscarTodos() {
        return repository.findAll();
    }

    @Override
    public void remover(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<DoacaoFundoMissionarioAla> salvarTodos(List<DoacaoFundoMissionarioAla> doacoesFundoMissionarioAla) {
        return repository.saveAll(doacoesFundoMissionarioAla);
    }
}
