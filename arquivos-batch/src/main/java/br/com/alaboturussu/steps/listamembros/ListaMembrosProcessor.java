package br.com.alaboturussu.steps.listamembros;

import br.com.alaboturussu.entity.Membro;
import br.com.alaboturussu.interfaces.IMembroService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class ListaMembrosProcessor implements ItemProcessor<List<String>, List<Membro>> {

    @Autowired
    private IMembroService membroService;

    @Override
    public List<Membro> process(List<String> linhas) {
//        DateTimeFormatter dateTimeFormatter = new DateTimeFormatterBuilder().parseCaseInsensitive().appendPattern("d MMM yyyy").toFormatter(Locale.US);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
        return linhas.stream().map(linha -> criarMembro(dateTimeFormatter, linha)).collect(Collectors.toList());
    }

    private Membro criarMembro(DateTimeFormatter formatter, String linha) {
        String[] dados = linha.split(";");
        String sobrenome = dados[0].split(",")[0].trim();
        String nome = dados[0].split(",")[1].trim();
        char sexo = dados[1].trim().charAt(0);
        LocalDate dataNascimento = LocalDate.parse(dados[2].trim(), formatter);
        return new Membro(nome, sobrenome, sexo, dataNascimento);
    }
}
