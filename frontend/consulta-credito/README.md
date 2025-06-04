
# consulta-credito - Frontend Angular

Este projeto é o frontend da aplicação **consulta-credito**, desenvolvido em Angular 19.

---

## Pré-requisitos

- Node.js v20.19.2 (para desenvolvimento local)
- Docker instalado (para build e execução via container)

---

## Build da aplicação

Para gerar a build de produção da aplicação Angular:

```bash
npm install
npm run build -- --configuration production
```

A build será gerada na pasta `dist/consulta-credito/browser`.

---

## Dockerização

Este projeto possui um **Dockerfile** configurado para:

1. Construir a aplicação Angular com Node.js.
2. Servir os arquivos estáticos com Nginx.

---

## Como rodar com Docker

### Passos:

1. Construir a imagem Docker:

```bash
docker build -t consulta-credito .
```

2. Rodar o container, mapeando a porta 4200 para a porta 80 do container (Nginx):

```bash
docker run -p 4200:80 consulta-credito
```

3. Acesse a aplicação no navegador em: [http://localhost:4200](http://localhost:4200)

---

## Observações

- O Nginx está configurado para servir a aplicação na porta 80 dentro do container.
- A porta 4200 está exposta para acesso local, pode ser alterada conforme necessidade.

---

## Contato

Para dúvidas ou sugestões, abra uma issue ou entre em contato.

---
