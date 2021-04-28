package br.com.alaparqueboturussu.core.entity;


import javax.persistence.ManyToOne;
import java.io.Serializable;

public class JustificacativaDizimo extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1075735240585251721L;

    @ManyToOne
    private Membro membro;
    private Integer mes;
    private Integer ano;
    private String descricao;
}