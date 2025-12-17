# Guia de Configuração Docker para ERP Sistema

## Pré-requisitos

- Docker (versão 20.10+)
- Docker Compose (versão 1.29+)

## Estrutura dos Arquivos Docker

```
├── Dockerfile              # Configuração multi-stage para buildear a aplicação
├── docker-compose.yml      # Orquestração com MySQL (produção)
├── docker-compose-h2.yml   # Orquestração com H2 (desenvolvimento)
├── .dockerignore           # Arquivos ignorados no build
└── init-db.sql            # Script SQL para inicializar banco de dados
```

## Como Usar

### Opção 1: Desenvolvimento com H2 (Recomendado para início rápido)

```bash
# Build e inicia a aplicação com H2 (banco de dados em memória)
docker-compose -f docker-compose-h2.yml up --build

# Acesse a aplicação em http://localhost:8080
```

**Vantagens:**
- ✅ Setup mais rápido
- ✅ Sem dependências externas
- ✅ Ideal para desenvolvimento
- ❌ Dados perdidos ao parar o container

### Opção 2: Produção/Staging com MySQL

```bash
# Build e inicia a aplicação com MySQL
docker-compose up --build

# Acesse a aplicação em http://localhost:8080
# MySQL disponível em localhost:3306
```

**Credenciais MySQL:**
- Host: `localhost` (ou `database` de dentro da rede Docker)
- Porta: `3306`
- Database: `erp_db`
- User: `erp_user`
- Password: `erp_password`

## Comandos Úteis

### Iniciar containers
```bash
docker-compose up
```

### Iniciar em background
```bash
docker-compose up -d
```

### Ver logs
```bash
docker-compose logs -f app
docker-compose logs -f database
```

### Parar containers
```bash
docker-compose down
```

### Parar e remover volumes (limpa dados)
```bash
docker-compose down -v
```

### Reconstruir imagem
```bash
docker-compose build --no-cache
```

### Executar comando no container
```bash
docker-compose exec app bash
docker-compose exec database mysql -u root -proot_password erp_db
```

## Acessar MySQL

```bash
# Do host
mysql -h 127.0.0.1 -u erp_user -perp_password erp_db

# Do container
docker-compose exec database mysql -u erp_user -perp_password -D erp_db
```

## Configuração de Variáveis de Ambiente

No arquivo `docker-compose.yml`, você pode alterar:

```yaml
environment:
  - JAVA_OPTS=-Xmx1024m -Xms512m  # Memória JVM
  - DB_URL=jdbc:h2:mem:erp_db     # URL do banco
  - DB_DRIVER=org.h2.Driver        # Driver JDBC
  - DB_USER=sa                      # Usuário
  - DB_PASSWORD=                    # Senha
```

## Troubleshooting

### Port 8080 já está em uso
```bash
# Mudar porta no docker-compose.yml
ports:
  - "8081:8080"  # Host:Container
```

### Port 3306 já está em uso
```bash
# Mudar porta do MySQL
ports:
  - "3307:3306"  # Host:Container
```

### Erro de conexão com banco de dados
1. Verificar se containers estão rodando: `docker-compose ps`
2. Ver logs: `docker-compose logs database`
3. Verificar nome do host (usar `database` em vez de `localhost`)

### Limpar tudo
```bash
docker-compose down -v --remove-orphans
docker volume prune
docker system prune
```

## Performance

### Aumentar memória JVM
```yaml
environment:
  - JAVA_OPTS=-Xmx2048m -Xms1024m
```

### Limitar recursos
```yaml
services:
  app:
    deploy:
      resources:
        limits:
          cpus: '1'
          memory: 1G
        reservations:
          cpus: '0.5'
          memory: 512M
```

## Próximos Passos

1. Atualizar `applicationContext.xml` para usar variáveis de ambiente
2. Configurar Hibernate para usar MySQL (atualmente usa H2)
3. Adicionar volumes para persistência de dados
4. Configurar nginx como reverse proxy (opcional)
5. Configurar logging centralizado (ELK stack, etc)

## Referências

- [Docker Documentation](https://docs.docker.com/)
- [Docker Compose Documentation](https://docs.docker.com/compose/)
- [Tomcat Docker Image](https://hub.docker.com/_/tomcat)
- [MySQL Docker Image](https://hub.docker.com/_/mysql)
