package br.com.alaboturussu.interfaces;

import br.com.alaboturussu.entity.Membro;

public interface IMembroService extends br.com.alaboturussu.interfaces.IGenericService<Membro> {

    Membro buscarPorNomeESobrenome(String nome, String sobrenome);
    Membro buscarPorNumero(String numero);
}
