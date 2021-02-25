package br.com.alaboturussu.steps.doacoes;

import br.com.alaboturussu.entity.DoacaoCategoriaOutros;
import br.com.alaboturussu.entity.DoacaoDizimo;
import br.com.alaboturussu.entity.DoacaoFundoMissionarioAla;
import br.com.alaboturussu.entity.DoacaoOfertaJejum;
import br.com.alaboturussu.interfaces.IDoacaoCategoriaOutrosService;
import br.com.alaboturussu.interfaces.IDoacaoDizimoService;
import br.com.alaboturussu.interfaces.IDoacaoFundoMissionarioAlaService;
import br.com.alaboturussu.interfaces.IDoacaoOfertaJejumService;
import br.com.alaboturussu.model.Doacao;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DoacoesWriter implements ItemWriter<List<Doacao>> {

    @Autowired
    private IDoacaoDizimoService dizimoService;
    @Autowired
    private IDoacaoCategoriaOutrosService categoriaOutrosService;
    @Autowired
    private IDoacaoFundoMissionarioAlaService fundoMissionarioAlaService;
    @Autowired
    private IDoacaoOfertaJejumService ofertaJejumService;

    @Value("${arquivo.caminho}")
    private String caminhoArquivo;

    @Override
    public void write(List<? extends List<Doacao>> list) throws IOException {
        if (list.isEmpty() || list.get(0).isEmpty()) {
            moverArquivo();
            return;
        }

        List<Doacao> doacaos = list.stream()
                .flatMap(List::stream)
                .collect(Collectors.toList());

        List<DoacaoDizimo> dizimos = new ArrayList<>();
        List<DoacaoOfertaJejum> ofertasJejum = new ArrayList<>();
        List<DoacaoFundoMissionarioAla> fundosMissionarioAla = new ArrayList<>();
        List<DoacaoCategoriaOutros> outros = new ArrayList<>();

        for (Doacao doacao : doacaos) {
            if (doacao instanceof DoacaoDizimo) {
                dizimos.add((DoacaoDizimo) doacao);
            } else if (doacao instanceof DoacaoOfertaJejum) {
                ofertasJejum.add((DoacaoOfertaJejum) doacao);
            } else if (doacao instanceof DoacaoFundoMissionarioAla) {
                fundosMissionarioAla.add((DoacaoFundoMissionarioAla) doacao);
            } else if (doacao instanceof DoacaoCategoriaOutros) {
                outros.add((DoacaoCategoriaOutros) doacao);
            }
        }
        dizimoService.salvarTodos(dizimos);
        categoriaOutrosService.salvarTodos(outros);
        ofertaJejumService.salvarTodos(ofertasJejum);
        fundoMissionarioAlaService.salvarTodos(fundosMissionarioAla);

        moverArquivo();
    }

    private void moverArquivo() throws IOException {
        Path arquivo = Paths.get(this.caminhoArquivo + "doacoes_membros.csv");
        Path destino = Paths.get(this.caminhoArquivo + "/lido/doacoes_membros.csv");
        Files.move(arquivo, destino);
    }
}
