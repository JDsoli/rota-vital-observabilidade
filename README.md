# Rota Vital - Observabilidade

Projeto individual de Infraestrutura de Comunicacao - CESAR School.

## Aplicacao original

Rota Vital, desenvolvido no Projeto Integrador:

https://github.com/HResende23/Rota-Vital

A aplicacao original foi desenvolvida em equipe.
A instrumentacao de observabilidade e objeto deste trabalho individual.

## Tecnologias

- Java 17 e Spring Boot
- PostgreSQL
- Spring Boot Actuator e Micrometer
- Prometheus
- Grafana
- Node Exporter
- cAdvisor
- Loki
- Grafana Alloy
- Tempo
- OpenTelemetry Java Agent
- Docker Compose

## Como executar

Instale o Docker Desktop com WSL 2.

Crie o arquivo de ambiente:

    Copy-Item .env.example .env

Configure senhas proprias no arquivo .env.

Suba o ambiente:

    docker compose up -d --build

Verifique os containers:

    docker compose ps

## Enderecos locais

| Servico | Endereco |
|---|---|
| Rota Vital | http://localhost:8080 |
| Actuator | http://localhost:8081/actuator/health |
| Metricas | http://localhost:8081/actuator/prometheus |
| Prometheus | http://localhost:9090 |
| Grafana | http://localhost:3000 |

## Servicos internos

- PostgreSQL: 5432
- Node Exporter: 9100
- cAdvisor: 8080
- Loki: 3100
- Tempo: 3200, 4317 e 4318

## Metricas de negocio

| Metrica | Tipo | Descricao |
|---|---|---|
| rotavital_bolsas_cadastradas | Gauge | Total de bolsas no banco |
| rotavital_requisicoes_cadastradas | Gauge | Total de requisicoes no banco |

## Logs

No Grafana Explore, selecione Loki e consulte:

    {application="rota-vital"}

Para consultar erros:

    {application="rota-vital"} |= "Erro controlado"

## Traces

O OpenTelemetry Java Agent instrumenta as requisicoes HTTP e
operacoes JDBC, enviando traces ao Tempo.

Os logs apresentam o traceId para correlacao no Grafana.

## Geracao de carga

O container gerador-carga gera requisicoes automaticamente.

Tambem e possivel executar:

    powershell -ExecutionPolicy Bypass -File scripts/gerar-carga.ps1

Os endpoints de demonstracao sao habilitados apenas na configuracao
do ambiente de observabilidade.

## Infraestrutura

O ambiente utiliza Docker Desktop com WSL 2.
Node Exporter e cAdvisor monitoram a VM Linux do Docker.

O cAdvisor foi atualizado para corrigir a incompatibilidade
com o armazenamento overlayfs e cgroups v2.

## Seguranca

As credenciais ficam no arquivo .env, ignorado pelo Git.
As portas publicadas localmente utilizam 127.0.0.1.
Somente o Grafana deve ser disponibilizado remotamente.
Nao publique credenciais no repositorio.

## Autor

Jardel Simplicio de Oliveira Junior
ADS - CESAR School - 2026.2