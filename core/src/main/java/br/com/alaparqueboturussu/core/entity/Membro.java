package br.com.alaparqueboturussu.core.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.opencsv.bean.CsvBindByPosition;
import com.opencsv.bean.CsvDate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

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
    @CsvBindByPosition(position = 1)
    private String nome;
    @CsvBindByPosition(position = 0)
    private String sobrenome;
    private String numero;
    @CsvBindByPosition(position = 2)
    private char sexo;
    @CsvBindByPosition(position = 3)
    @CsvDate("d MMM yyyy")
    @JsonFormat(pattern="yyyy-MM-dd", timezone="America/Sao_Paulo")
    private LocalDate dataNascimento;
    private boolean ativo;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "membro_id")
    @JsonIgnore
    private List<DoacaoDizimo> dizimos;

    public Membro(String nome, String sobrenome, char sexo, LocalDate dataNascimento) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.sexo = sexo;
        this.dataNascimento = dataNascimento;
    }

    public int getIdade() {
        return Period.between(this.dataNascimento, LocalDate.now()).getYears();
    }

}
