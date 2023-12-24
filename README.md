## Resumo do Backend de Upload de Fotos com Spring Boot

Este aplicativo backend Spring Boot facilita o upload de fotos por meio de pontos de extremidade RESTful. Aqui está um resumo dos principais componentes:

# FotoController

## Endpoints

### Upload de Foto

**Endpoint:**
```
POST http://localhost:8080/fotos
```

**Descrição:**
Este endpoint permite o upload de uma foto.

**Parâmetros:**
- `foto`: Arquivo da foto a ser enviado.

---

### Obter Foto

**Endpoint:**
```
GET http://localhost:8080/fotos/{filename}
```

**Descrição:**
Este endpoint permite obter uma foto existente pelo nome do arquivo.

**Parâmetros:**
- `filename`: Nome do arquivo da foto.

---

### Deletar Foto

**Endpoint:**
```
DELETE http://localhost:8080/fotos/{filename}
```

**Descrição:**
Este endpoint permite excluir uma foto existente pelo nome do arquivo.

**Parâmetros:**
- `filename`: Nome do arquivo da foto a ser excluída.
