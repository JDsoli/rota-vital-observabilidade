package br.com.rotavital.controller;

import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import br.com.rotavital.repository.BolsaHemoComponenteRepository;
import br.com.rotavital.repository.RequisicaoRepository;

@RestController
@RequestMapping("/observabilidade")
@ConditionalOnProperty(
    name = "observabilidade.demo.enabled",
    havingValue = "true"
)
public class ObservabilidadeController {

    private static final Logger log =
        LoggerFactory.getLogger(ObservabilidadeController.class);

    private final BolsaHemoComponenteRepository bolsas;
    private final RequisicaoRepository requisicoes;

    public ObservabilidadeController(
            BolsaHemoComponenteRepository bolsas,
            RequisicaoRepository requisicoes) {
        this.bolsas = bolsas;
        this.requisicoes = requisicoes;
    }

    @GetMapping("/teste")
    public Map<String, Long> teste() {

        log.info("Iniciando consulta de indicadores");

        long totalBolsas = bolsas.count();
        long totalRequisicoes = requisicoes.count();

        log.info("Consulta de indicadores concluida");

        return Map.of(
            "bolsas", totalBolsas,
            "requisicoes", totalRequisicoes
        );
    }

    @GetMapping("/erro")
    public void erro() {
        log.error("Erro controlado de demonstracao");

        throw new ResponseStatusException(
            HttpStatus.INTERNAL_SERVER_ERROR,
            "Erro controlado"
        );
    }
}