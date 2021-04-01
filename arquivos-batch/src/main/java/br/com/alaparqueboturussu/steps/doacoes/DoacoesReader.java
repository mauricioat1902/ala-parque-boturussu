package br.com.alaparqueboturussu.steps.doacoes;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.MalformedInputException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Slf4j
@Component
public class DoacoesReader implements ItemReader<List<String>> {

    @Value("${arquivo.caminho}")
    private String caminhoArquivo;

    @Override
    public List<String> read() throws IOException {
        Path arquivo = Paths.get(this.caminhoArquivo + "doacoes_membros.csv");
        if (!Files.exists(arquivo))
            return null;

        List<String> linhasArquivo;
        try {
            linhasArquivo = Files.readAllLines(arquivo, StandardCharsets.UTF_8);
        } catch (MalformedInputException ex) {
            linhasArquivo = Files.readAllLines(arquivo, StandardCharsets.ISO_8859_1);
        }
        return linhasArquivo;
    }
}
