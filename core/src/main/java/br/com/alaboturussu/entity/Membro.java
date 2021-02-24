package br.com.alaboturussu.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table
public class Membro implements Serializable {
    private static final long serialVersionUID = -5406840424110638798L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String sobrenome;
    private String numero;
    private char sexo;
    private LocalDate dataNascimento;

    public Membro(String nome, String sobrenome, char sexo, LocalDate dataNascimento) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.sexo = sexo;
        this.dataNascimento = dataNascimento;
    }
}
