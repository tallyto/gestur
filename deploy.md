# Fazendo deploy no fly.io


### Cria um banco de dados
```bash
fly postgres create
```

### Cria uma aplicação no fly.io
```bash
fly launch --no-deploy
```
### Attach a database to project
```bash
fly postgres create attach project_name
```
### Create a build to project
```bash
mvn install -DskipTests
```
### Deploy application
```bash
fly deploy
```