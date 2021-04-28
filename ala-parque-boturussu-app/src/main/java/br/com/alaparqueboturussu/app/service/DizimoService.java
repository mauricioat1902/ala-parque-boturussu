package br.com.alaparqueboturussu.app.service;

import br.com.alaparqueboturussu.app.dto.SituacaoDoador;
import br.com.alaparqueboturussu.app.enums.SituacaoDoadorEnum;
import br.com.alaparqueboturussu.app.interfaces.IDizimoService;
import br.com.alaparqueboturussu.core.dto.MembroDTO;
import br.com.alaparqueboturussu.core.entity.DoacaoDizimo;
import br.com.alaparqueboturussu.core.entity.Membro;
import br.com.alaparqueboturussu.core.interfaces.IMembroService;
import br.com.alaparqueboturussu.core.model.Doacao;
import br.com.alaparqueboturussu.core.util.ObjectMapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class DizimoService implements IDizimoService {

    @Autowired
    private IMembroService membroService;

    @Override
    public List<SituacaoDoador> buscarSituacaoDoadoresPorAno(Integer ano) {
        List<Membro> membrosAtivos = membroService.buscarPorAtivo(true);

        LocalDate dataInicial = LocalDate.of(ano, Month.JANUARY, 1);
        LocalDate dataFinal = LocalDate.now().getYear() == ano ? LocalDate.now() : LocalDate.of(ano, Month.DECEMBER, 31);
        long numeroMesesAno = ChronoUnit.MONTHS.between(dataInicial, dataFinal);

        return membrosAtivos
                .stream()
                .map(membro -> {
                    List<DoacaoDizimo> dizimosAno = membro.getDizimos().stream().filter(filtrarDoacoesAno(dataInicial, dataFinal)).collect(Collectors.toList());
                    int situacaoDoador = obterSituacaoDoador(numeroMesesAno, dizimosAno);
                    dizimosAno.addAll(gerarListaDizimosComtodosMeses(ano, membro, dizimosAno));
                    dizimosAno = dizimosAno.stream().sorted(Comparator.comparing(Doacao::getData)).collect(Collectors.toList());
                    return new SituacaoDoador(ObjectMapperUtils.map(membro, MembroDTO.class), dizimosAno, situacaoDoador);
                })
                .sorted(Comparator.comparing(doacao -> doacao.getMembro().getNomeCompleto()))
                .collect(Collectors.toList());
    }

    private List<DoacaoDizimo> gerarListaDizimosComtodosMeses(Integer ano, Membro membro, List<DoacaoDizimo> dizimosAno) {
        List<DoacaoDizimo> dizimos = new ArrayList<>();
        Month mesAtual = LocalDate.now().getMonth();
        for (Month month : Month.values()) {
            if (month.compareTo(mesAtual) > 0)
                break;
            if (dizimosAno.stream().noneMatch(doacaoDizimo -> doacaoDizimo.getData().getMonth().compareTo(month) == 0)) {
                DoacaoDizimo dizimoVazio = new DoacaoDizimo();
                dizimoVazio.setMembro(membro);
                dizimoVazio.setData(LocalDate.of(ano, month.getValue(), 1));
                dizimos.add(dizimoVazio);
            }
        }
        return dizimos;
    }

    private int obterSituacaoDoador(long numeroMesesAno, List<DoacaoDizimo> dizimosAno) {
        int totalDizimos = dizimosAno.size();
        if (totalDizimos >= numeroMesesAno)
            return SituacaoDoadorEnum.INTEGRAL.getCodigo();
        else if (totalDizimos < numeroMesesAno && totalDizimos > 0)
            return SituacaoDoadorEnum.PARCIAL.getCodigo();
        else
            return SituacaoDoadorEnum.NAO_DIZIMISTA.getCodigo();
    }

    private Predicate<DoacaoDizimo> filtrarDoacoesAno(LocalDate dataInicial, LocalDate dataFinal) {
        return doacaoDizimo -> doacaoDizimo.getData().isEqual(dataInicial) || doacaoDizimo.getData().isEqual(dataFinal)
                || (doacaoDizimo.getData().isAfter(dataInicial) && doacaoDizimo.getData().isBefore(dataFinal));
    }
}
