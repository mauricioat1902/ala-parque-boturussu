package br.com.alaparqueboturussu.controller;

import br.com.alaparqueboturussu.dto.SituacaoDoador;
import br.com.alaparqueboturussu.interfaces.IDizimoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/dizimo")
public class DizimoController {

    @Autowired
    private IDizimoService dizimoService;

    @GetMapping("/situacaoDoadores/ano/{ano}")
    public List<SituacaoDoador> buscarSituacaoDoadores(@PathVariable Integer ano) {
        return dizimoService.buscarSituacaoDoadoresPorAno(ano);
    }
}
