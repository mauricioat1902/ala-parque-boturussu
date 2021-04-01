package br.com.alaparqueboturussu.app.controller;

import br.com.alaparqueboturussu.app.dto.SituacaoDoador;
import br.com.alaparqueboturussu.app.interfaces.IDizimoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/dizimo")
public class DizimoController {

    @Autowired
    private IDizimoService dizimoService;

    @GetMapping("/situacaoDoadores/ano/{ano}")
    public List<SituacaoDoador> buscarSituacaoDoadores(@PathVariable Integer ano) {
        return dizimoService.buscarSituacaoDoadoresPorAno(ano);
    }
}
