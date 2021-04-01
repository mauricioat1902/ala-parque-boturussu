package br.com.alaparqueboturussu.steps.listamembros;

import br.com.alaparqueboturussu.core.entity.Membro;
import br.com.alaparqueboturussu.core.interfaces.IMembroService;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ListaMembrosWriter implements ItemWriter<List<Membro>> {

    @Autowired
    private IMembroService membroService;
    @Value("${arquivo.caminho}")
    private String caminhoArquivo;

    @Override
    public void write(List<? extends List<Membro>> listaMembros) throws IOException {
        if (listaMembros.isEmpty() || listaMembros.get(0).isEmpty()) {
            moverArquivo();
            return;
        }

        List<Membro> membrosArquivo = listaMembros.stream()
                .flatMap(List::stream)
                .collect(Collectors.toList());

        List<Membro> membrosGravados = membroService.buscarTodos();

        //TODO verificar
        List<Membro> membrosParaSalvar = membrosArquivo.stream().filter(membroArquivo -> membrosGravados
                .stream()
                .noneMatch(membroGravado ->
                        !membroGravado.getNome().equalsIgnoreCase(membroArquivo.getNome())
                                && !membroGravado.getSobrenome().equalsIgnoreCase(membroArquivo.getSobrenome())
                                && !membroGravado.getDataNascimento().isEqual(membroArquivo.getDataNascimento())
                )
        ).collect(Collectors.toList());

        membroService.salvarTodos(membrosParaSalvar);

        moverArquivo();
    }

    private void moverArquivo() throws IOException {
        Path arquivo = Paths.get(this.caminhoArquivo + "lista_membros.csv");
        Path destino = Paths.get(this.caminhoArquivo + "/lido/lista_membros.csv");
        Files.move(arquivo, destino);
    }
}
