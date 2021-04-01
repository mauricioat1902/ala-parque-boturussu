package br.com.alaparqueboturussu.app.interfaces;

import br.com.alaparqueboturussu.app.dto.SituacaoDoador;

import java.util.List;

public interface IDizimoService {

    List<SituacaoDoador> buscarSituacaoDoadoresPorAno(Integer ano);
}
