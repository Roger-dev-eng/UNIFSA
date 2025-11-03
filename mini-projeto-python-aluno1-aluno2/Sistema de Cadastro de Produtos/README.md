# Sistema de Gerenciamento de Estoque

Este é um sistema simples de **gerenciamento de estoque** desenvolvido em **Python**.  
Ele permite **cadastrar, listar, buscar, atualizar e excluir produtos** diretamente pelo terminal.

---

## Estrutura do Menu

-----Estoque dos produtos-----\
1 - Cadastrar produto\
2 - Listar produtos\
3 - Buscar produto\
4 - Atualizar produto\
5 - Excluir produto\
0 - Sair

## Exemplos

###  1. Cadastrar Produto
Adiciona um novo produto ao estoque com:
- Código
- Nome
- Preço
- Quantidade

**Exemplo de uso:**\
- Insira o código do produto: 1\
- Insira o nome do produto: Caneta\
- Insira o valor do produto: 2.5\
- Insira a quantidade: 100\
- Produto adicionado com sucesso.

###  2. Listar Produtos
Mostra todos os produtos cadastrados.

**Exemplo de saída:**\
{'código': 1, 'nome': 'Caneta', 'preço': 2.5, 'quantidade': 100}\
{'código': 2, 'nome': 'Lápis', 'preço': 1.5, 'quantidade': 200}