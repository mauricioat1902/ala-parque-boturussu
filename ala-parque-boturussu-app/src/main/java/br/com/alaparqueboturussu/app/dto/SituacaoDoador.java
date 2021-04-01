package br.com.alaparqueboturussu.app.dto;

import br.com.alaparqueboturussu.core.entity.DoacaoDizimo;
import br.com.alaparqueboturussu.core.entity.Membro;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
