package br.com.alaparqueboturussu.app.dto;

import br.com.alaparqueboturussu.core.dto.MembroDTO;
import br.com.alaparqueboturussu.core.entity.DoacaoDizimo;
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
    private MembroDTO membro;
    private List<DoacaoDizimo> dizimos;
    private Integer situacao;
}
