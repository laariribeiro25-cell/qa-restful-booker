# Testes de API com REST Assured

Projeto do curso de QA da DIO. Explorei a API Restful-booker no Postman e automatizei os testes em Java com JUnit 5 e REST Assured. Também usei o JSON Server para simular uma API de quartos e o Allure para gerar o relatório.

## Cobertura

- `GET /ping` verifica a disponibilidade da API.
- `POST /auth` testa credenciais válidas e inválidas.
- `GET /booking` verifica o status e os IDs da lista de reservas.
- `POST /booking` e `GET /booking/{id}` verificam a criação e a consulta de uma reserva.
- `PATCH /booking/{id}` e `PUT /booking/{id}` verificam a atualização parcial e completa.
- `DELETE /booking/{id}` exclui a reserva criada pelo teste. Uma consulta posterior verifica o retorno `404`.
- `GET /quartos?disponivel=true` testa o filtro de quartos disponíveis na API simulada.

## Como executar

Pré-requisitos: JDK 17 ou superior, Maven, Node.js com npm e acesso à internet para consultar a Restful-booker pública e baixar as dependências.

Na raiz do projeto, inicie o JSON Server em um terminal:

```powershell
npx.cmd --yes json-server@0.17.4 --watch .\src\test\resources\mock\db.json --host 127.0.0.1 --port 3005
```

Mantenha esse terminal aberto. Em outro terminal, na raiz do projeto, rode:

```powershell
mvn clean test
```

## Evidências

- [Coleção do Postman](postman/restful-booker.postman_collection.json). Importe o JSON e execute as requisições na ordem da coleção.
- [Relatório Allure](docs/relatorio-allure.html). Baixe o HTML e abra no navegador para ver a execução com 6 testes aprovados.

Para gerar um novo relatório, com o Allure CLI instalado, execute após os testes:

```powershell
allure generate target/allure-results --single-file --clean -o target/allure-report
```