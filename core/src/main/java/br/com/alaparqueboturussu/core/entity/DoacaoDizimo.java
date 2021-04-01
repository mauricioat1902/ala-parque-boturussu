package br.com.alaparqueboturussu.core.entity;

import br.com.alaparqueboturussu.core.model.Doacao;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table
public class DoacaoDizimo extends Doacao implements Serializable {
    private static final long serialVersionUID = -377448196747929096L;

}
