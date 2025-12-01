# ERP Sistema Híbrido (Struts 1.x, Spring, Hibernate)

Este projeto foi desenvolvido como um sistema de portfólio para demonstrar a integração de um conjunto de tecnologias legadas e intermediárias, conforme solicitado: **Java**, **Struts 1.x**, **Hibernate 5.x**, **Spring 4.x** (para Injeção de Dependência), **JSP/JSTL**, **jQuery** e **CSS**.

A arquitetura implementada segue o padrão MVC (Model-View-Controller), onde:
*   **Controller:** Implementado pelo **Struts 1.x ActionServlet** e classes `Action` (ex: `ProdutoController`).
*   **Service:** Implementado por classes de serviço (ex: `ProdutoService`), gerenciadas pelo **Spring** para Injeção de Dependência.
*   **Persistence (DAO):** Implementado por classes de repositório (ex: `ProdutoRepository`), que utilizam o **Hibernate** para operações CRUD.
*   **View:** Implementado por **JSP** e estilizado com **CSS** e interatividade com **jQuery**.

## Estrutura do Projeto

O projeto segue a estrutura Maven e a hierarquia de pacotes Java:

```
erp-sistema/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── erp/
│   │   │           ├── controller/ (Struts Actions e BaseAction para integração Spring)
│   │   │           ├── model/ (Entidades Hibernate: Produto, Estoque, Venda)
│   │   │           ├── repository/ (DAOs Hibernate e GenericDAO)
│   │   │           └── service/ (Serviços Spring para lógica de negócio)
│   │   │           └── ErpApplication.java (Placeholder)
│   │   ├── resources/
│   │   │   └── hibernate.cfg.xml (Configuração do Hibernate)
│   │   └── webapp/
│   │       ├── static/ (CSS, JS/jQuery)
│   │       ├── templates/ (JSPs específicos)
│   │       ├── index.jsp (Página inicial)
│   │       └── WEB-INF/
│   │           ├── web.xml (Configuração do Servlet Struts e Listener Spring)
│   │           ├── applicationContext.xml (Configuração do Spring DI e SessionFactory)
│   │           └── struts-config.xml (Mapeamento de Actions Struts)
├── pom.xml (Dependências Maven)
└── README.md
```

## Tecnologias Utilizadas

| Camada | Tecnologia | Versão | Função no Projeto |
| :--- | :--- | :--- | :--- |
| **Controller** | Struts | 1.3.10 | Recebe requisições e delega para a camada de Serviço. |
| **DI/Integração** | Spring Core/Web/ORM | 4.3.30.RELEASE | Injeção de dependência (DI) e gerenciamento de beans de Serviço. |
| **Persistência** | Hibernate Core | 5.4.32.Final | Mapeamento Objeto-Relacional (ORM) e operações CRUD. |
| **Banco de Dados** | H2 Database | 1.4.200 | Banco de dados em memória para desenvolvimento e testes. |
| **View** | JSP/JSTL | 2.2/1.2 | Geração dinâmica de conteúdo HTML. |
| **Frontend** | jQuery | 3.6.0 | Manipulação do DOM e interatividade no lado do cliente. |
| **Estilo** | CSS | - | Estilização da interface. |

## Como Executar o Projeto

O projeto é empacotado como um arquivo WAR e deve ser implantado em um servidor de aplicação Java (como Apache Tomcat).

1.  **Pré-requisitos:** Java Development Kit (JDK 8 ou superior) e Apache Maven.
2.  **Compilação:** Navegue até o diretório `erp-sistema/` e execute o comando Maven para compilar e empacotar:
    ```bash
    mvn clean package
    ```
    Isso gerará o arquivo `target/erp-sistema.war`.
3.  **Implantação:** Copie o arquivo `erp-sistema.war` para o diretório `webapps` do seu servidor Tomcat.
4.  **Acesso:** Inicie o Tomcat. A aplicação estará acessível em:
    ```
    http://localhost:8080/erp-sistema/
    ```
    (A porta pode variar dependendo da sua configuração do Tomcat).

## Funcionalidade Demonstrada

A funcionalidade de **Gestão de Produtos** está implementada para demonstrar o fluxo completo:

1.  Acesse `http://localhost:8080/erp-sistema/produtos.do?action=listar`.
2.  O `ProdutoController` (Struts Action) é executado.
3.  O Controller obtém o `ProdutoService` (Spring Bean) via injeção.
4.  O Service chama o `ProdutoRepository` (Hibernate DAO) para listar os produtos.
5.  A lista é exibida no `produtos.jsp`.
6.  O botão "Adicionar Produto de Teste" permite persistir novos dados no banco H2 em memória, demonstrando a escrita no banco de dados.

O uso do **jQuery** é demonstrado no arquivo `static/js/app.js`, que adiciona uma mensagem de rodapé na página inicial após o carregamento do DOM.

---
*Este projeto foi criado por Manus AI para fins de demonstração de portfólio.*
