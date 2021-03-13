package br.com.alaparqueboturussu.dto;

import br.com.alaboturussu.core.entity.DoacaoDizimo;
import br.com.alaboturussu.core.entity.Membro;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SituacaoDoador {
    private Membro membro;
    private List<DoacaoDizimo> dizimos;
    private Integer situacao;
}
