package br.com.alaparqueboturussu.steps.doacoes;

import br.com.alaparqueboturussu.core.entity.DoacaoCategoriaOutros;
import br.com.alaparqueboturussu.core.entity.DoacaoDizimo;
import br.com.alaparqueboturussu.core.entity.DoacaoFundoMissionarioAla;
import br.com.alaparqueboturussu.core.entity.DoacaoOfertaJejum;
import br.com.alaparqueboturussu.core.entity.Membro;
import br.com.alaparqueboturussu.core.interfaces.IDoacaoCategoriaOutrosService;
import br.com.alaparqueboturussu.core.interfaces.IDoacaoDizimoService;
import br.com.alaparqueboturussu.core.interfaces.IDoacaoFundoMissionarioAlaService;
import br.com.alaparqueboturussu.core.interfaces.IDoacaoOfertaJejumService;
import br.com.alaparqueboturussu.core.interfaces.IMembroService;
import br.com.alaparqueboturussu.core.model.Doacao;
import br.com.alaparqueboturussu.utils.Utils;
import javassist.NotFoundException;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Component
public class DoacoesProcessor implements ItemProcessor<List<String>, List<Doacao>> {

    public static final String SEPARADOR = ";";
    @Autowired
    private IMembroService membroService;
    @Autowired
    private IDoacaoDizimoService dizimoService;
    @Autowired
    private IDoacaoCategoriaOutrosService categoriaOutrosService;
    @Autowired
    private IDoacaoFundoMissionarioAlaService fundoMissionarioAlaService;
    @Autowired
    private IDoacaoOfertaJejumService ofertaJejumService;

    @Override
    public List<Doacao> process(List<String> linhas) throws NotFoundException {
        List<DoacaoDizimo> dizimosSalvos = dizimoService.buscarTodos();
        List<DoacaoCategoriaOutros> categoriaOutrosSalvos = categoriaOutrosService.buscarTodos();
        List<DoacaoFundoMissionarioAla> fundosMissionarioAlaSalvos = fundoMissionarioAlaService.buscarTodos();
        List<DoacaoOfertaJejum> ofertasJejumSalvas = ofertaJejumService.buscarTodos();

        List<Doacao> doacaos = new ArrayList<>();
        Membro membro = null;
        DateTimeFormatter formatter = new DateTimeFormatterBuilder().parseCaseInsensitive().appendPattern("dd MMM yyyy").toFormatter();
        for (String linha : linhas) {
            if (!linha.startsWith("Doações")) {
                membro = extrairMembro(linha);
            } else {
                preencherListaDeDoacoes(dizimosSalvos, categoriaOutrosSalvos, fundosMissionarioAlaSalvos, ofertasJejumSalvas, doacaos, membro, formatter, linha);
            }
        }
        return doacaos;
    }

    private void preencherListaDeDoacoes(List<DoacaoDizimo> dizimosSalvos, List<DoacaoCategoriaOutros> categoriaOutrosSalvos, List<DoacaoFundoMissionarioAla> fundosMissionarioAlaSalvos, List<DoacaoOfertaJejum> ofertasJejumSalvas, List<Doacao> doacaos, Membro membro, DateTimeFormatter formatter, String linha) {
        String[] linhaSplit = linha.split(SEPARADOR);
        LocalDate dataDoacao = LocalDate.parse(linhaSplit[1], formatter);
        BigDecimal valorDizimo = Utils.converterBigDecimal(linhaSplit[2]);
        BigDecimal valorOfertaJejum =  Utils.converterBigDecimal(linhaSplit[3]);
        BigDecimal valorFundoMissionarioAla =  Utils.converterBigDecimal(linhaSplit[4]);
        BigDecimal valorOutros =  Utils.converterBigDecimal(linhaSplit[5]);

        if (valorDizimo.compareTo(BigDecimal.ZERO) != 0) {
            boolean contemDoacao = dizimosSalvos.stream().anyMatch(encontrarDoacaoSalva(dataDoacao, valorDizimo, membro));
            if (!contemDoacao) {
                doacaos.add(criarDizimo(membro, dataDoacao, valorDizimo));
            }
        }
        if (valorOfertaJejum.compareTo(BigDecimal.ZERO) != 0) {
            boolean contemDoacao = ofertasJejumSalvas.stream().anyMatch(encontrarDoacaoSalva(dataDoacao, valorOfertaJejum, membro));
            if (!contemDoacao) {
                doacaos.add(criarOfertaJejum(membro, dataDoacao, valorOfertaJejum));
            }
        }
        if (valorFundoMissionarioAla.compareTo(BigDecimal.ZERO) != 0) {
            boolean contemDoacao = fundosMissionarioAlaSalvos.stream().anyMatch(encontrarDoacaoSalva(dataDoacao, valorFundoMissionarioAla, membro));
            if (!contemDoacao) {
                doacaos.add(criarFundoMissionarioAla(membro, dataDoacao, valorFundoMissionarioAla));
            }
        }
        if (valorOutros.compareTo(BigDecimal.ZERO) != 0) {
            boolean contemDoacao = categoriaOutrosSalvos.stream().anyMatch(encontrarDoacaoSalva(dataDoacao, valorOutros, membro));
            if (!contemDoacao) {
                doacaos.add(criarCategoriaOutros(membro, dataDoacao, valorOutros));
            }
        }
    }

    private DoacaoCategoriaOutros criarCategoriaOutros(Membro membro, LocalDate dataDoacao, BigDecimal valorOutros) {
        DoacaoCategoriaOutros doacaoCategoriaOutros = new DoacaoCategoriaOutros();
        doacaoCategoriaOutros.setMembro(membro);
        doacaoCategoriaOutros.setData(dataDoacao);
        doacaoCategoriaOutros.setValor(valorOutros);
        return doacaoCategoriaOutros;
    }

    private DoacaoFundoMissionarioAla criarFundoMissionarioAla(Membro membro, LocalDate dataDoacao, BigDecimal valorFundoMissionarioAla) {
        DoacaoFundoMissionarioAla doacaoFundoMissionarioAla = new DoacaoFundoMissionarioAla();
        doacaoFundoMissionarioAla.setMembro(membro);
        doacaoFundoMissionarioAla.setData(dataDoacao);
        doacaoFundoMissionarioAla.setValor(valorFundoMissionarioAla);
        return doacaoFundoMissionarioAla;
    }

    private DoacaoOfertaJejum criarOfertaJejum(Membro membro, LocalDate dataDoacao, BigDecimal valorOfertaJejum) {
        DoacaoOfertaJejum doacaoOfertaJejum = new DoacaoOfertaJejum();
        doacaoOfertaJejum.setMembro(membro);
        doacaoOfertaJejum.setData(dataDoacao);
        doacaoOfertaJejum.setValor(valorOfertaJejum);
        return doacaoOfertaJejum;
    }

    private DoacaoDizimo criarDizimo(Membro membro, LocalDate dataDoacao, BigDecimal valorDizimo) {
        DoacaoDizimo doacaoDizimo = new DoacaoDizimo();
        doacaoDizimo.setMembro(membro);
        doacaoDizimo.setData(dataDoacao);
        doacaoDizimo.setValor(valorDizimo);
        return doacaoDizimo;
    }

    private Predicate<Doacao> encontrarDoacaoSalva(LocalDate dataDoacao, BigDecimal valor, Membro membro) {
        return dizimoSalvo ->
                dizimoSalvo.getMembro().equals(membro)
                        && dizimoSalvo.getData().compareTo(dataDoacao) == 0
                        && dizimoSalvo.getValor().compareTo(valor) == 0;
    }

    private Membro extrairMembro(String linha) throws NotFoundException {
        String[] linhaSplit = linha.split(SEPARADOR);

        String sobrenome = linhaSplit[0].trim();
        String nome = linhaSplit[1].trim();
        String numeroMembro = linhaSplit[2].trim();

        Membro membro = membroService.buscarPorNumero(numeroMembro);
        if (membro == null) {
            membro = membroService.buscarPorNomeESobrenome(nome, sobrenome);

            if (membro == null)
                throw new NotFoundException("Membro não encontrado");

            membro.setNumero(numeroMembro);
            membro = membroService.salvar(membro);
        }

        return membro;
    }
}
