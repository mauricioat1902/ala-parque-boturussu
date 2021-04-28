package br.com.alaparqueboturussu.core.model;

import br.com.alaparqueboturussu.core.entity.Membro;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@MappedSuperclass
public abstract class Doacao {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    @ManyToOne
    @JsonIgnore
    private Membro membro;
    private BigDecimal valor;
    @JsonFormat(pattern="yyyy-MM-dd", timezone="America/Sao_Paulo")
    private LocalDate data;

}
