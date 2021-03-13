package br.com.alaboturussu.core.interfaces;

import br.com.alaboturussu.core.entity.DoacaoDizimo;

import java.time.LocalDate;
import java.util.List;

public interface IDoacaoDizimoService extends IGenericService<DoacaoDizimo> {
    List<DoacaoDizimo> buscarEntreDatas(LocalDate dataInicial, LocalDate dataFinal);
}
