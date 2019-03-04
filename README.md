# Back-end Cliente-Vendedor Engineering do Brasil

## Pré Requisitos

1. **JDK versão 8** instalado e o parâmetro JAVA_HOME "setado";

2. **Maven** instalado.

## Como Executar o Projeto

Na pasta principal do projeto *(/engdb)* execute: **mvn spring-boot:run**.

## Como Executar os Testes Unitários

Na pasta principal do projeto *(/engdb)* execute: **mvn test**.

## Banco de Dados

A aplicação roda com o banco de dados de teste H2. Para acessar o banco digite no *browser* **_http://localhost:8080/h2-console/login.jsp_**. Preencha os seguintes campos:

* **Driver Class**: org.h2.Driver
* **JDBC URL**: jdbc:h2:mem:testdb
* **User Name**: sa
* **Password**:

Foram salvos alguns dados iniciais no banco de dados para que possamos fazer os testes manuais de maneira mais rápida. O banco é instanciado com 3 vendedores e 7 clientes.

## Endpoints

Existe uma documentação que foi gerada de forma automática com Swagger. Para visualizá-la rode a aplicação e digite no seu *browser* **_http://localhost:8080/swagger-ui.html_**.

### Clientes

| Verbo Http | Endpoint | Descrição |
|------------|----------|-----------|
| GET | /clientes/{id} | Busca por id |
| GET | /clientes/cpf?value=12345678932 | Busca por CPF |
| GET | /clientes/vendedorId?value={id} | Busca cliente pelo id do vendedor |
| GET | /clientes/page | Retorna todos clientes com paginação |
| GET | /clientes/pageWithVendedor | Retorna todos os clientes com vendedores usando paginação |
| POST | /clientes | Insere cliente |
| PUT | /clientes/{id} | Atualiza cliente |
| DELETE | /clientes/{id} | Remove cliente |

### Vendedores

| Verbo Http | Endpoint | Descrição |
|------------|----------|-----------|
| GET | /vendedores/{id} | Busca por id |
| GET | /vendedores/cpf?value=12345678932 | Busca por CPF |
| GET | /vendedores/page | Retorna todos os vendedores com paginação |
| POST | /vendedores | Insere vendedor |
| PUT | /vendedores/{id} | Atualiza vendedor |
| DELETE | /vendedores/{id} | Remove vendedor |

## Testes Unitários

Ao todo, foram feitos 33 testes na camada de resource que abrangem o sistema de ponta a ponta.

### Clientes

| Nome do Método |
|-------------|
| givenClienteId_whenGetCliente_thenStatus200 |
| givenClienteCpf_whenGetCliente_thenStatus200 |
| givenClienteIdNotExist_whenGetCliente_thenStatus404 |
| givenClienteCpfNotExist_whenGetCliente_thenStatus404 |
| givenClienteNewDtoValid_whenPostCliente_thenStatus201 |
| givenClienteNewDtoInvalidName_whenPostCliente_thenStatus422 |
| givenClienteNewDtoInvalidCpf_whenPostCliente_thenStatus422 |
| givenClienteNewDtoInvalidVendedorId_whenPostCliente_thenStatus404 |
| givenClienteNewDtoInvalidSexo_whenPostCliente_thenStatus422 |
| givenClienteUpdtDtoValid_whenPutCliente_thenStatus204 |
| givenClienteUpdtDtoInvalidSexo_whenPutCliente_thenStatus422 |
| givenClienteUpdtDtoInvalidNameWithWhiteSpaces_whenPutCliente_thenStatus422 |
| givenClienteUpdtDtoExistNameButdifferentId_whenPutCliente_thenStatus422 |
| givenFindAllClientes_whenGetClientes_thenStatus200 |
| givenFindAllClientes_whenGetClientesWithVendedores_thenStatus200 |
| givenVendedorId_whenGetClientesByVendedorId_thenStatus200 |
| givenVendedorId_whenDeleteCliente_thenStatus204 |
| givenClienteNewDtoWhichIsAVendedor_whenPostCliente_thenStatus201 |

### Prestadores

| Nome do Método |
|-------------|
| givenVendedorId_whenGetVendedor_thenStatus200 |
| givenVendedorCpf_whenGetVendedor_thenStatus200 |
| givenVendedorIdNotExist_whenGetVendedor_thenStatus404 |
| givenVendedorCpfNotExist_whenGetVendedor_thenStatus404 |
| givenVendedorNewDtoValid_whenPostVendedor_thenStatus201 |
| givenVendedorNewDtoInvalidName_whenPostVendedor_thenStatus422 |
| givenVendedorNewDtoInvalidCpf_whenPostVendedor_thenStatus400 |
| givenVendedorNewDtoInvalidCpfAndName_whenPostVendedor_thenStatus422 |
| givenVendedorNewDtoDuplicatedCpf_whenPostVendedor_thenStatus422 |
| givenVendedorNewDtoDuplicatedName_whenPostVendedor_thenStatus422 |
| givenVendedorUpdtDtoValid_whenPutVendedor_thenStatus204 |
| givenVendedorUpdtDtoInvalidName_whenPutVendedor_thenStatus422 |
| givenVendedorUpdtDtoExistName_whenPutVendedor_thenStatus422 |
| givenFindAllVendedores_whenGetVendedores_thenStatus200 |
| givenVendedorId_whenDeleteVendedor_thenStatus204 |
| givenVendedorNewDtoWhichIsACliente_whenPostVendedor_thenStatus201 |

## Referências

1. Para geração de pessoas utilizei o *link* do [4devs](https://www.4devs.com.br/gerador_de_pessoas).

2. [Postman](https://www.getpostman.com/).

3. Para implementação do código foram diversos *links* utilizados, os principais foram:

* [Professor Nelio Alves](https://www.udemy.com/spring-boot-ionic/);
* [Baeldung](https://www.baeldung.com/).
