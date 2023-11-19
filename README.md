# Para testar:

### 1 - Crie um database mysql chamado test
### 2 - Edite: "spring.datasource.username" e "spring.datasource.password" em application.properties com as suas credenciais de usuario do seu banco mysql.
### 3- Execute a aplicação: O spring irá criar automaticamente as tabelas.
### 4 - Execute o conteúdo do arquivo migration.sql no mysql workbench ou diretamente no terminal.
### Neste ponto, voce poderá usar o postman para realizar as requests. A rota é: 'localhost:8080/order'. Segue um exemplo de payload para o método POST (criar pedido):
```json
{
    "clientId": 1,
    "items": [
        {
            "controlNumber": 1,
            "registrationDate": "2023-11-19",
            "productName": "productName_24a291fc977c",
            "value": 10.00,
            "quantity": 0
        },
        {
            "controlNumber": 2,
            "registrationDate": "2023-11-19",
            "productName": "productName_24a291fc977c",
            "value": 10.00,
            "quantity": 0
        }
    ],
    "totalValue": 0.00
}
```
### O get tem como queryparams, os seguintes: 'orderNumber' (id), 'registationDate' (este leva em conta as ordens cujos itens foram registrados na data) e 'all' como default, ao não passar nenhum dos parametros anteriores.