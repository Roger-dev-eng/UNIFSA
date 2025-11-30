# Biblioteca 'time' para deixar mais agradável visualmente para o usuário
import time

# Lista para os dicionários dos produtos, 'set' para evitar produtos repetidos e categorias disponíveis para classificar os produtos
estoque = []
codigos_cadastrados = set()
categorias_disponiveis = ("Alimentos", "Limpeza", "Bebidas")

# Função para validar palavras vazias e números
def validar_nome(nome):
    if not isinstance(nome, str):
        raise ValueError("O nome deve ser uma string.")
    nome_limpo = nome.strip()
    if nome_limpo == "":
        raise ValueError("Não deve ser um nome vazio. Insira um nome para continuar!")
    if nome_limpo.isdigit():
        raise ValueError("Não insira números no nome.")
    return nome_limpo

# Função para validar o tipo do código e códigos já cadastratdos
def validar_codigo(codigo):
    try:
        codigo = int(codigo)
    except ValueError:
        raise ValueError("O código deve ser um número inteiro.")
    if codigo in codigos_cadastrados:
        raise ValueError("Código já cadastrado. Tente outro.")
    return codigo

# Validação do tipo do valor e valores negativos
def validar_preco(valor):
    try:
        preco = float(valor)
    except ValueError:
        raise ValueError("O preço deve ser um número válido.")
    if preco < 0:
        raise ValueError("O preço não pode ser negativo.")
    return preco

# Validação do tipo da quantidade e valores negativos
def validar_quantidade(qtd):
    try:
        qtd = int(qtd)
    except ValueError:
        raise ValueError("A quantidade deve ser um número inteiro.")
    if qtd < 0:
        raise ValueError("A quantidade não pode ser negativa.")
    return qtd

# Função de cadastro de produtos com os tratamentos de erros
def cadastrar_item():
    produto = {}
    while True:
        try:
            codigo = input("Insira o código do produto: ")
            codigo = validar_codigo(codigo)
            produto["código"] = codigo
            codigos_cadastrados.add(codigo)
            break
        except ValueError as e:
            print(e)

    while True:
        try:
            nome = input("Insira o nome do produto: ")
            produto["nome"] = validar_nome(nome)
            break
        except ValueError as e:
            print(e)

    while True:
        try:
            preco = input("Insira o preço do produto: ")
            produto["preço"] = validar_preco(preco)
            break
        except ValueError as e:
            print(e)

    while True:
        try:
            qtd = input("Insira a quantidade do produto: ")
            produto["quantidade"] = validar_quantidade(qtd)
            break
        except ValueError as e:
            print(e)
    print("Categorias disponíveis:")
    for i, categoria in enumerate(categorias_disponiveis, start=1):
        print(f"{i} - {categoria}")

    while True:
        try:
            escolha = int(input("Escolha o número da categoria: "))
            if 1 <= escolha <= len(categorias_disponiveis):
                produto["categoria"] = categorias_disponiveis[escolha - 1]
                break
            else:
                print("Escolha inválida. Tente novamente.")
        except ValueError:
            print("Digite um número válido.")
    estoque.append(produto)
    print("Produto adicionado com sucesso!")
    time.sleep(1)

# Função para listar apenas um produto específico
def buscar_item():
    buscar_nome = input("Digite o nome do produto que deseja procurar: ")
    for produto in estoque:
        if produto['nome'] == buscar_nome:
            print(produto)
            break
    else:
        print("Produto não encontrado")
    time.sleep(1)

# Função para atualizar produto com os tratamentos de erros
def atualizar_item():
    atualizar_produto = input("Digite o nome do produto que deseja atualizar: ")
    for produto in estoque:
        if produto['nome'].lower() == atualizar_produto.lower():
            print("Produto encontrado:")
            print(produto)
            print("--- Deixe em BRANCO para manter o valor atual ---")

            novo_codigo = input("Novo código: ")
            if novo_codigo.strip():
                try:
                    codigo = validar_codigo(novo_codigo)
                    if codigo != produto['código']:
                        codigos_cadastrados.discard(produto['código'])
                        codigos_cadastrados.add(codigo)
                        produto['código'] = codigo
                except ValueError as e:
                    print(e)
                    print("Mantendo o código atual.")

            novo_nome = input("Novo nome: ")
            if novo_nome.strip():
                try:
                    produto['nome'] = validar_nome(novo_nome)
                except ValueError as e:
                    print(e)
                    print("Mantendo o nome atual.")

            novo_preco = input("Novo preço: ")
            if novo_preco.strip():
                try:
                    produto['preço'] = validar_preco(novo_preco)
                except ValueError as e:
                    print(e)
                    print("Mantendo o preço atual.")

            nova_quantidade = input("Nova quantidade: ")
            if nova_quantidade.strip():
                try:
                    produto['quantidade'] = validar_quantidade(nova_quantidade)
                except ValueError as e:
                    print(e)
                    print("Mantendo a quantidade atual.")

            print("Categorias disponíveis:")
            for i, categoria in enumerate(categorias_disponiveis, start=1):
                print(f"{i} - {categoria}")
            nova_categoria = input("Nova categoria (número): ")
            if nova_categoria.strip():
                try:
                    escolha = int(nova_categoria)
                    if 1 <= escolha <= len(categorias_disponiveis):
                        produto['categoria'] = categorias_disponiveis[escolha - 1]
                    else:
                        print("Categoria inexistente, mantendo atual.")
                except ValueError:
                    print("Categoria inválida, mantendo atual.")
            print("Produto atualizado com sucesso!")
            print(produto)
            time.sleep(1)
            return
    print("Produto não encontrado.")
    time.sleep(1)

# Função para listar todos os itens
def listar_item():
    if not estoque:
        print("Estoque vazio")
    else:
        for produto in estoque:
            print(produto)
    time.sleep(1)    

# Função para remover produto
def remover_item():
    deletar_produto = input("Digite o nome do produto que deseja deletar: ")
    for produto in estoque:
        if produto['nome'] == deletar_produto:
            print("Produto encontrado:")
            print(produto)
            exclusao = input("Deseja realmente excluir esse produto? s/n ")
            if exclusao == 's':
                estoque.remove(produto)
                codigos_cadastrados.discard(produto['código'])
                print("Produto excluído com sucesso")
            else:
                print("Exclusão cancelada.")
            break
    else:
        print("Produto não encontrado.")
    time.sleep(1)

# Função principal
def menu():
  while True:
      print("-----Estoque dos produtos-----")
      print("1 - Cadastrar produto")
      print("2 - Listar produtos")
      print("3 - Buscar produto")
      print("4 - Atualizar produto")
      print("5 - Remover produto")
      print("0 - Sair")

      try:
          escolha = int(input("Digite um número(0-5): "))
      except ValueError:
          print("Erro. Digite apenas um número de 1 a 5!")
          continue

      if escolha == 1:
          cadastrar_item()
      elif escolha == 2:
          listar_item()
      elif escolha == 3:
          buscar_item()
      elif escolha == 4:
          atualizar_item()
      elif escolha == 5:
          remover_item()
      elif escolha == 0:
          break
      else:
          print("Erro! Digite um número válido.")
menu()

