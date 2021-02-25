package br.com.alaboturussu.steps.listamembros;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Slf4j
@Component
public class ListaMembrosReader implements ItemReader<BufferedReader> {

    @Value("${arquivo.caminho}")
    private String caminhoArquivo;

    @Override
    public BufferedReader read() throws IOException {
        Path arquivo = Paths.get(this.caminhoArquivo + "lista_membros.csv");
        if (!Files.exists(arquivo))
            return null;

        return Files.newBufferedReader(arquivo);
    }
}
