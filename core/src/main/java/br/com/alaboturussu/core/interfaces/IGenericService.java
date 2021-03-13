package br.com.alaboturussu.core.interfaces;

import java.util.List;

public interface IGenericService<T> {

    T salvar(T objeto);
    T buscarPorId(Long id);
    List<T> buscarTodos();
    void remover(Long id);
    List<T> salvarTodos(List<T> objetos);

}
