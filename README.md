# simian-api
API responsável por validar se uma sequência de DNA pertence a um humano ou a um simian.

Para rodar a aplicação é necessário seguir os passos abaixo.

## Configurar o banco de dados
- Acessar a pasta **database-docker** e rodar o comando abaixo:

    > sudo docker-compose up -d

- Criar a tabela **analysis_data** 

    Conectar no banco de dados utilizando o client de sua preferência, como por exemplo o JBeaver, e executar o script de criação da tabela analysis-data-table que consta no arquivo `database-docker/create-analysis-data-table.sql`.

## Rodar a aplicação
    
- No diretório raiz do projeto executar o comando do maven para baixar as dependências:
    
    > mvn clean install

- Rodar a aplicação utilizando o comando abaixo:

  > ./mvnw spring-boot:run

# Testar sequência de DNA
  
Utilizar um **REST Client** de sua preferência, como por exemplo o Postman, e realizar uma chamada para o endpoint abaixo:

POST → localhost:8080/simian

- Exemplo de chamada para um DNA humano
```
{
    "dna": ["GCTGAC", "CGACTG", "ACTGAC", "AGACTG", "TCTGAC", "TCACTG"]
}
```

- Exemplo de chamada para um DNA simian
```
{
    "dna": ["AAAACC", "CTGACT", "TGCACT", "GACTGA", "ACTGAC", "TGACTG"]
}
```

# Analisar resultado dos testes

Utilizar um **REST Client** de sua preferência, como por exemplo o Postman, e realizar uma chamada para o endpoint abaixo:

GET → localhost:8080/stats

A API deve retornar um json semelhante ao exemplo abaixo:

```
{
    "count_mutant_dna": 5,
    "count_human_dna": 6,
    "ratio": 0.8333333333333334
}
```