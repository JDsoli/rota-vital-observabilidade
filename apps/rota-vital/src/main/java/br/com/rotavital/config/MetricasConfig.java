package br.com.rotavital.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.binder.MeterBinder;

import br.com.rotavital.repository.BolsaHemoComponenteRepository;
import br.com.rotavital.repository.RequisicaoRepository;

@Configuration
public class MetricasConfig {

    @Bean
    public MeterBinder metricasNegocio(
            BolsaHemoComponenteRepository bolsas,
            RequisicaoRepository requisicoes) {

        return registry -> {

            Gauge.builder(
                    "rotavital.bolsas.cadastradas",
                    bolsas,
                    repository -> repository.count())
                .description("Total de bolsas cadastradas")
                .register(registry);

            Gauge.builder(
                    "rotavital.requisicoes.cadastradas",
                    requisicoes,
                    repository -> repository.count())
                .description("Total de requisicoes cadastradas")
                .register(registry);
        };
    }
}