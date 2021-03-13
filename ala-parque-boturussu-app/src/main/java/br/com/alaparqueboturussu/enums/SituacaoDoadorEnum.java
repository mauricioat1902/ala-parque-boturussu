package br.com.alaparqueboturussu.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SituacaoDoadorEnum {
    INTEGRAL(1),
    PARCIAL(2),
    NAO_DIZIMISTA(3);

    private final int codigo;
}
