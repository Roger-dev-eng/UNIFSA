# Sistema de Controle de produtos
Este projeto é um sistema de gerenciamento de estoque desenvolvido em Python.
Ele permite cadastrar, atualizar, buscar, listar, gerar relatório e remover produtos, com tratamento de erros e validações para evitar entradas inválidas.
O sistema funciona totalmente no terminal e utiliza estruturas básicas como listas, dicionários e funções. 
Tem como objetivo praticar manipulação de listas e dicionários, trabalhar com funções e modularização, e por último, aplicar validação e tratamento de erros
## Funcionalidades do Sistema

### Cadastrar produto

Adiciona um novo item ao estoque, incluindo:
- Código
- Nome
- Preço
- Quantidade
- Categoria

Cada campo possui validação para evitar erros e valores inválidos.

### Listar produtos
Exibe todos os itens cadastrados no estoque.
### Buscar produto
Procura um produto pelo nome e mostra seus dados.

### Atualizar produto

Permite modificar qualquer campo de um item já cadastrado.
O usuário pode deixar qualquer campo em branco para manter o valor atual.

### Gerar relatório
Calcula e exibe um resumo geral do estoque, incluindo quantidade total de produtos, soma das quantidades, valor total estimado, categorias presentes e quais são o produto mais caro e o mais barato.

### Remover produto

Apaga um item do estoque após confirmação do usuário.

### Menu interativo

Menu principal com as opções:
```bash
1 - Cadastrar produto
2 - Listar produtos
3 - Buscar produto
4 - Atualizar produto
5 - Remover produto
6 - Relatório do estoque
0 - Sair
```
## Estruturas utilizadas
### estoque
Lista que armazena todos os produtos como dicionários.\
Exemplo de item:
```bash
{
  "código": 101,
  "nome": "Arroz",
  "preço": 9.50,
  "quantidade": 20,
  "categoria": "Alimentos"
}
```
### codigos_cadastrados
Set usado para garantir que nenhum código seja duplicado no estoque.
### categorias_disponiveis
Tupla contendo as categorias válidas para o cadastro.

## Validações (Funções de Tratamento)
O sistema possui funções próprias para validar entradas e impedir erros:
### validar_nome(nome)
Garante que:
- seja uma string
- não esteja vazia
- não seja só números
- remove espaços desnecessários
### validar_codigo(codigo)

- transforma a entrada em inteiro
- impede códigos duplicados
- impede valores inválidos

### validar_preco(preco)

- impede valores não numéricos
- impede valores negativos

### validar_quantidade(qtd)

- impede qualquer valor não inteiro
- impede valores negativos

## Como executar
Rode no terminal:
```bash
python main.py
```

## Intergrantes
- Roger Oliveira Feitosa
- Erick Ruan Nunes Vieira
