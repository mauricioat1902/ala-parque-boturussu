package br.com.alaboturussu.core.interfaces;

import br.com.alaboturussu.core.entity.Membro;

import java.util.List;

public interface IMembroService extends IGenericService<Membro> {

    Membro buscarPorNomeESobrenome(String nome, String sobrenome);
    Membro buscarPorNumero(String numero);
    List<Membro> buscarPorAtivo(boolean ativo);
}
