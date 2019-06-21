# Sistema

### Iniciando o servidor

Para iniciar o servidor, execute o comando abaixo.

*Lembrando que é necessário ter o PostgreSQL rodando com um database chamado 'jns', com usuário 'postgres' e senha 'root'.*

```
mvn spring-boot:run
```

E acesse-o em [http://localhost:8080/jns/](http://localhost:8080/jns/).

Por padrão, existe apenas um usuário (username 'admin' e senha 'admin') e não é possível cadastrar novos usuários.

### Utilizando a API

Em suma, existem endpoints para CRUD das 4 entidades (filme, pessoa, premiação e prêmio) e um endpoint para autenticação.

Com o servidor iniciado, a 'documentação' da API pode ser acessada em:
[http://localhost:8080/jns/swagger-ui.html](http://localhost:8080/jns/swagger-ui.html).
