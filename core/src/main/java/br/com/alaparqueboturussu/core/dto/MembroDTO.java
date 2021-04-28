package br.com.alaparqueboturussu.core.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MembroDTO {

    private Long id;
    private String nome;
    private String sobrenome;
    private String numero;
    private char sexo;
    @JsonFormat(pattern="yyyy-MM-dd", timezone="America/Sao_Paulo")
    private LocalDate dataNascimento;
    private boolean ativo;
    private int idade;

    public String getNomeCompleto() {
        return this.nome + " " + this.sobrenome;
    }
}
