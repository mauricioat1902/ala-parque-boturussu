package br.com.alaboturussu.steps.listamembros;

import br.com.alaboturussu.core.entity.Membro;
import br.com.alaboturussu.core.interfaces.IMembroService;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.util.List;

@Slf4j
@Component
public class ListaMembrosProcessor implements ItemProcessor<BufferedReader, List<Membro>> {

    @Autowired
    private IMembroService membroService;

    @Override
    public List<Membro> process(BufferedReader arquivoReader) {
        return new CsvToBeanBuilder(arquivoReader)
                .withSeparator(';')
                .withType(Membro.class)
                .build()
                .parse();
    }

}
