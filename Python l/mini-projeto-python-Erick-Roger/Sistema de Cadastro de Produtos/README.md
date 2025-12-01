#  Sistema de Controle de Estoque em Python

##  Descrição

O **Sistema de Controle de Estoque** é um programa desenvolvido em **Python** que permite gerenciar produtos de forma simples, utilizando o terminal.  
O usuário pode cadastrar, listar, buscar, atualizar e excluir produtos de um estoque armazenado em memória durante a execução do programa.

Esse sistema foi ideal para fins **educacionais**, ajudando a compreender conceitos de **listas, dicionários, funções, loops e controle de fluxo** em Python.

---

##  Funcionalidades

###  1. Cadastro de produtos
Permite adicionar novos produtos ao estoque informando:
- Código  
- Nome  
- Preço  
- Quantidade  

Os produtos são armazenados em uma lista chamada `estoque`, onde cada item é um dicionário, por exemplo:

```python
{
  'código': 101,
  'nome': 'Caneta Azul',
  'preço': 2.50,
  'quantidade': 100
} 
```

### 2. Listar produtos

Exibe todos os produtos cadastrados no estoque.
Se não houver produtos, o sistema informa "Estoque vazio".

### 3. Buscar produto

Procura um produto pelo nome exato e exibe seus detalhes, se encontrado.
Caso contrário, exibe "Produto não encontrado".

### 4. Atualizar produto

Permite alterar as informações de um produto existente (código, nome, preço e quantidade).

O usuário pode deixar um campo em branco para manter o valor atual.

O sistema valida entradas numéricas para preço e quantidade.

### 5. Excluir produto

Permite excluir um produto do estoque pelo nome.
Antes da exclusão, o sistema pede uma confirmação (s para sim, n para não).
Se o produto não existir, o sistema informa "Produto não encontrado".

### 0. Sair

Encerra o programa.

## Estrutura de Dados

- Estoque (list):\
Lista principal onde todos os produtos são armazenados.
Cada produto é um dicionário contendo:
```python
{
    "código": int,
    "nome": str,
    "preço": float,
    "quantidade": int
}
```
## Exemplos de Uso
### Exemplo 1 — Cadastro e listagem de produtos

Entrada:
```python
1
Insira o código do produto: 1001
Insira o nome do produto: Caderno
Insira o valor do produto: 12.5
Insira a quantidade: 20

2
# Saída esperada:

Produto adicionado com sucesso.
{'código': 1001, 'nome': 'Caderno', 'preço': 12.5, 'quantidade': 20}