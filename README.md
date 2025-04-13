# 🚀 srv-integracao-hubspot - Desafio Técnico Completo

[![Licença](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)  
[![Build Status](https://img.shields.io/badge/Build-Passing-brightgreen.svg)](#)  
[![Java Version](https://img.shields.io/badge/Java-17-blue.svg)](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)  
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.4.4-brightgreen.svg)](https://spring.io/projects/spring-boot)  
[![Docker](https://img.shields.io/badge/Docker-Enabled-blue.svg)](https://www.docker.com/)  
[![Render](https://img.shields.io/badge/Render-Cloud%20Hosting-blue.svg)](https://render.com/)  
[![Deploy Status](https://img.shields.io/badge/Deploy-Live%20on%20Render-blueviolet)](https://srv-integracao-hubspot.onrender.com)

---

## 📘 Visão Geral do Projeto

Este repositório contém a implementação de um microserviço Java criado para um desafio técnico, com objetivo de realizar a **integração com a plataforma HubSpot**, usando as melhores práticas de arquitetura de software, segurança, rastreabilidade, testes e manutenção.

A solução contempla:
- Integração via OAuth 2.0
- Consumo de APIs CRM (Contatos) via Feign Client
- Webhook para eventos de contato criado
- Validação segura da assinatura HMAC-SHA256 v3
- Deploy com Docker em Render.com

Arquitetura, testes, logging, traceability e resiliência foram pontos de foco fundamentais. Todo o planejamento foi realizado no Jira com histórias, tarefas e cenários de testes associados.

---

## 🌐 Arquitetura Hexagonal e Estrutura

A escolha da **arquitetura hexagonal (Ports and Adapters)** permite isolar o domínio e regras de negócio dos detalhes de infraestrutura e tecnologias, garantindo testabilidade, escalabilidade e clareza.  
O projeto possui separação clara entre:

- **`adapters/input`**: Controllers (REST)
- **`adapters/output`**: Feign clients
- **`application`**: Casos de uso e portas
- **`domain`**: Modelos e exceções
- **`infrastructure`**: Clients, propriedades, utils, filtros, handlers, etc.

A estrutura de pacotes foi definida com base em padrões de mercado (DDD + Hexagonal + Clean Architecture).

---

## 🎓 Tecnologias e Dependências

| Categoria            | Tecnologias                                                                  |
|----------------------|-------------------------------------------------------------------------------|
| Linguagem            | Java 17                                                                       |
| Framework Principal  | Spring Boot 3.4.4                                                             |
| Cliente HTTP         | Spring OpenFeign                                                              |
| Autenticação         | OAuth2 Client + Spring Security                                              |
| Cache                | Caffeine (para tokens e corpos de requisições)                                |
| Documentação API     | springdoc-openapi (Swagger 2.3.0)                                            |
| Build                | Maven 3.9+                                                                    |
| Testes               | JUnit 5 + Mockito                                                             |
| Validação            | Bean Validation (JSR-380)                                                    |
| Deploy               | Docker + Render.com                                                          |
| Logs                 | Logback + Correlation ID                                                     |
| Tratamento de erros  | ExceptionHandler global + mapeamento de erro do HubSpot                     |

---

## ⚖️ Pré-Requisitos

- Java 17+
- Maven 3.9+
- Conta gratuita em https://developers.hubspot.com/
  - Conta de Teste - Para testa minha aplicação
  - Conta de Desenvolvedor - Se quiser testar localmente a aplicação
- Criação de App com escopos: `crm.objects.contacts.write`, `oauth`, `crm.objects.contacts.read`
- Configuração de variáveis de ambiente:

```properties
HUBSPOT_CLIENT_ID=xxxxxx
HUBSPOT_CLIENT_SECRET=xxxxxx
HUBSPOT_REDIRECT_URI=http://localhost:8080/v1/oauth/callback
HUBSPOT_SCOPE=contacts%20oauth%20webhook
```

---

## 🔧 Como Executar

### 🌐 Ambiente de Produção (Render.com)

A aplicação está disponível publicamente em:

> [https://srv-integracao-hubspot.onrender.com](https://srv-integracao-hubspot.onrender.com)

#### Testando no Ambiente de Produção:
1. Acesse a **documentação Swagger UI**:  
   [https://srv-integracao-hubspot.onrender.com/swagger-ui/index.html](https://srv-integracao-hubspot.onrender.com/swagger-ui/index.html)  
   Aqui você pode visualizar e interagir com os endpoints disponíveis.
2. Utilize ferramentas como **Postman** ou **Insomnia** para enviar requisições aos endpoints da URL de produção.

### 🚀 Via Docker

```bash
docker build -t hubspot-service -f docker/Dockerfile .
docker run -p 8080:8080  -e HUBSPOT_CLIENT_ID=xxx  -e HUBSPOT_CLIENT_SECRET=yyy  -e HUBSPOT_SCOPE=crm.objects.contacts.write%20oauth%20crm.objects.contacts.read  -e HUBSPOT_REDIRECT_URI=http://localhost:8080/v1/oauth/callback  hubspot-service
```

### ⚡ Local (Maven)

```bash
git clone https://github.com/seu-usuario/srv-integracao-hubspot.git
cd srv-integracao-hubspot
./mvnw spring-boot:run
```
Nota: Para rodar localmente ou via Docker, é necessário tem uma conta de desenvolvedor na Hubspot, já que para ter acesso aos dados das variaveis de ambiente você precisa ter um aplicativo criado e condigurado para isso. Além de que talvez tenha disivuldade de testar o endpoint do webhook já que o Hubspot só consegue notificar URL que estjam expostas, serviõs que estão rodando somente na sua maquina não recebem notificação , para isso pode criar um tunel de acesso para sua porta, insidoc ulitizar o ngork.

---

## 🧪 Fluxo de Teste

### 1. Geração de URL OAuth
A primeira etapa para interagir com a API do HubSpot é obter uma URL de autorização OAuth.

- Gere a URL acessando o endpoint específico (definido na documentação Swagger) diretamente no navegador.
- Exemplos de URLs:
    - **Ambiente local**: `http://localhost:8080/...`
    - **Ambiente de produção**: `https://srv-integracao-hubspot.onrender.com/...`
- Siga as instruções fornecidas pela API para completar o fluxo de autorização.

### 2. Criação de Contato
Após finalizar o fluxo de autorização, você terá o `access-token` em mãos.

- Utilize o endpoint `/v1/contato/criar` para criar um contato no HubSpot.
- Ferramentas recomendadas: **Postman** ou **Insomnia**.
- Envie uma requisição `POST` para o endpoint mencionado com os dados necessários.


### 3. Webhook
Após criar um contato, o HubSpot enviará uma notificação via webhook para o serviço.

- O serviço retornará um **status code 200**.
- Caso esteja rodando localmente, você poderá visualizar os logs da aplicação com os dados do evento recebido.

#### Dica:
Se estiver testando localmente, utilize ferramentas como **ngrok** para expor sua aplicação e permitir que o HubSpot envie notificações para o webhook.

---

## 🔍 Swagger e Documentação

- OpenAPI JSON: https://srv-integracao-hubspot.onrender.com/swagger.json
  - Após realizar a chamada vai ser gerado um json, onde você pode usar o site swagger.io (https://editor.swagger.io/) para visualização.

Contratos da API foram extraídos para classes de `@interface` separadas seguindo boas práticas para reutilização e leitura.

**Documentação técnica consta no repositório.**

---

## 📂 Webhook Assinatura (Segurança)

- Implementado filtro personalizado para validar o header `X-HubSpot-Signature-v3`
- Usa corpo da requisição (cached), URI e timestamp para compor string canônica
- Algoritmo: `HMAC SHA-256` + `Base64`
- Valida diferença de tempo máxima de 5 minutos

---

## 💪 Resiliência e Robustez

- Filtro de `CorrelationId`
- Logs estruturados com contexto
- Mecanismo de retry com Spring Retry (caso APIs estejam momentaneamente indisponíveis)
- Padronização de exceções com DTO de erro padronizado (ApiErrorResponse)

---

## 🛠️ Melhorias Futuras

- 🧩 Cobertura total de testes + mocks para endpoints
    - Testes Unitários com Mockito + JUnit
    - Testes de integração usando contextos reais
    - Estrutura pronta para CI com GitHub Actions
    - Planejado para passar por:
        - SonarQube
        - Fortify
        - Mend
        - OWASP ZAP
- 🚩 Observabilidade: Prometheus + Grafana, Zipkin, etc
- 📝 Inclusão de testes de contrato (Spring Cloud Contract ou Pact)
- 🧱 Modularização e Split por Contexto (Domain Driven Design)
- ♻️ Integração com ferramenta de audit logging

---

## 📋 Planejamento no Jira

As tarefas foram organizadas e entregues com base em:

- Epics: Integração OAuth, Consumo de Contato, Webhook
- Stories: Criação de endpoints, filtros, cache, tratamento de erros, log
- Tasks: Configuração de Swagger, Docker, Handler, ExceptionMapper, Retentativa
- Cenários de Teste: manuais e planejados por cada rota de execução

---
## 👨‍💻 Autor
<img style="border-radius: 20%;" src="https://i.imgur.com/D2pGHCO.jpeg" width="100px;" alt=""/>
 <br />

**Evert Barros**  
> Desenvolvido com paixão, foco em engenharia de software e padrões profissionais.

[![LinkedIn](https://img.shields.io/badge/-LinkedIn-blue?logo=linkedin&logoColor=white)](https://www.linkedin.com/in/evertbarros/)
[![GitHub](https://img.shields.io/badge/-GitHub-181717?logo=github&logoColor=white)](https://github.com/EvertBarrosT)
[![Email](https://img.shields.io/badge/-Email-red?logo=gmail&logoColor=white)](mailto:evertbarros@hotmail.com)

---

## 🔒 Licença

Este repositório está licenciado sob os termos da [MIT License](https://opensource.org/licenses/MIT).
