package br.com.alaparqueboturussu.interfaces;

import br.com.alaparqueboturussu.dto.SituacaoDoador;

import java.util.List;

public interface IDizimoService {

    List<SituacaoDoador> buscarSituacaoDoadoresPorAno(Integer ano);
}
