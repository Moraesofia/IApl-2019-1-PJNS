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

### TODO list

Abaixo estão listados os principais entregáveis do projeto, a fim de esclarecer o status atual.

* ~~Geral: uso de pacotes~~
* ~~Geral: código-fonte em inglês~~
* ~~Geral: persistência~~
* ~~Geral: tratamento de exceção~~ *(ainda não avaliado pelo docente)*
* ~~Geral: integridade referencial~~ *(ainda não avaliado pelo docente)*
* **Geral: implantar no servidor do INF**
* ~~Sistema web: internacionalização~~
* ~~Sistema web: mensagens de advertência, erro etc.~~
* ~~Sistema web: login/autenticação~~
* ~~Sistema web: CRUDs~~
* ~~Produtor: protocolo RESTFul~~ *(ainda não avaliado pelo docente)*
* ~~Produtor: formato JSON~~ *(ainda não avaliado pelo docente)*
* ~~Produtor: login/autenticação~~ *(ainda não avaliado pelo docente)*
* ~~Produtor: CRUDs~~ *(ainda não avaliado pelo docente)*
* ~~Produtor: informar como consumir~~ *(ainda não avaliado pelo docente)*
* **Consumidor: consumir a API da outra equipe?**
* **Consumidor: exibir interface web para API consumida?**