package br.com.alaparqueboturussu.service;

import br.com.alaboturussu.core.entity.DoacaoDizimo;
import br.com.alaboturussu.core.entity.Membro;
import br.com.alaboturussu.core.interfaces.IMembroService;
import br.com.alaparqueboturussu.dto.SituacaoDoador;
import br.com.alaparqueboturussu.enums.SituacaoDoadorEnum;
import br.com.alaparqueboturussu.interfaces.IDizimoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;
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
        LocalDate dataFinal = LocalDate.of(ano, Month.DECEMBER, 31);
        long numeroMesesAno = ChronoUnit.MONTHS.between(dataFinal, dataFinal);

        return membrosAtivos
                .stream()
                .map(membro -> {
                    List<DoacaoDizimo> dizimosAno = membro.getDizimos().stream().filter(filtrarDoacoesAno(dataInicial, dataFinal)).collect(Collectors.toList());
                    int situacaoDoador = obterSituacaoDoador(numeroMesesAno, dizimosAno);
                    return new SituacaoDoador(membro, dizimosAno, situacaoDoador);
                }).collect(Collectors.toList());
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
