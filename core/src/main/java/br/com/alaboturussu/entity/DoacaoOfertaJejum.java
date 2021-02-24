package br.com.alaboturussu.entity;

import br.com.alaboturussu.model.Doacao;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table
public class DoacaoOfertaJejum extends Doacao implements Serializable {
    private static final long serialVersionUID = -6749692477435340175L;
}
