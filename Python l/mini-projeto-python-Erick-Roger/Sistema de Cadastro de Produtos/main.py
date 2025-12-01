import time

estoque = []
codigos_cadastrados = set()
categorias_disponiveis = ("Alimentos", "Limpeza", "Bebidas")

def cadastro():
    produto = {}
    while True:
        try:
            codigo = int(input("Insira o código do produto: "))
            if codigo in codigos_cadastrados:
                print("Código já cadastrado, tente outro.")
                continue
            produto['código'] = codigo
            codigos_cadastrados.add(codigo)
            break
        except ValueError:
            print("Digite um número válido para o código.")
    produto['nome'] = input("Insira o nome do produto: ")
    produto['preço'] = float(input("Insira o valor do produto: "))
    produto['quantidade'] = int(input("Insira a quantidade: "))
    print("Categorias disponíveis:")
    for i, categoria in enumerate(categorias_disponiveis, start=1):
        print(f"{i} - {categoria}")
    while True:
        try:
            escolha = int(input("Escolha o número da categoria: "))
            if 1 <= escolha <= len(categorias_disponiveis):
                produto['categoria'] = categorias_disponiveis[escolha - 1]
                break
            else:
                print("Escolha inválida.")
        except ValueError:
            print("Digite um número válido.")
    estoque.append(produto)
    print("Produto adicionado com sucesso.")
    time.sleep(1)

def listar():
    if not estoque:
        print("Estoque vazio")
    else:
        for produto in estoque:
            print(produto)
    time.sleep(1)

def buscar():
    buscar_nome = input("Digite o nome do produto que deseja procurar: ")
    for produto in estoque:
        if produto['nome'] == buscar_nome:
            print(produto)
            break
    else:
        print("Produto não encontrado")
    time.sleep(1)

def atualizar():
    atualizar_produto = input("Digite o nome do produto que deseja atualizar: ")
    for produto in estoque:
        if produto['nome'] == atualizar_produto:
            print("Produto encontrado:")
            print(produto)
            print("--- Deixe em BRANCO para manter o valor atual ---")
            novo_codigo = input("Novo código: ")
            if novo_codigo:
                try:
                    novo_codigo = int(novo_codigo)
                    if novo_codigo in codigos_cadastrados and novo_codigo != produto['código']:
                        print("Código já existente, mantendo o atual.")
                    else:
                        codigos_cadastrados.discard(produto['código'])
                        codigos_cadastrados.add(novo_codigo)
                        produto['código'] = novo_codigo
                except ValueError:
                    print("Código inválido, mantendo valor atual.")
            novo_nome = input("Novo nome: ")
            if novo_nome:
                produto['nome'] = novo_nome
            novo_preco = input("Novo preço: ")
            if novo_preco:
                try:
                    produto['preço'] = float(novo_preco)
                except ValueError:
                    print("Preço inválido, mantendo valor atual")
            nova_quantidade = input("Nova quantidade: ")
            if nova_quantidade:
                try:
                    produto['quantidade'] = int(nova_quantidade)
                except ValueError:
                    print("Quantidade inválida, mantendo valor atual.")
            print("Categorias disponíveis:")
            for i, categoria in enumerate(categorias_disponiveis, start=1):
                print(f"{i} - {categoria}")
            nova_categoria = input("Nova categoria (número): ")
            if nova_categoria:
                try:
                    escolha = int(nova_categoria)
                    if 1 <= escolha <= len(categorias_disponiveis):
                        produto['categoria'] = categorias_disponiveis[escolha - 1]
                except ValueError:
                    print("Categoria inválida, mantendo valor atual.")
            print("Produto atualizado com sucesso!")
            print(produto)
            break
    else:
        print("Produto não encontrado.")
    time.sleep(1)

def excluir():
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

while True:
    print("-----Estoque dos produtos-----")
    print("1 - Cadastrar produto")
    print("2 - Listar produtos")
    print("3 - Buscar produto")
    print("4 - Atualizar produto")
    print("5 - Excluir produto")
    print("0 - Sair")

    try:
        escolha = int(input("Digite um número(0-5): "))
    except ValueError:
        print("Erro. Digite apenas um número de 1 a 5!")
        continue

    if escolha == 1:
        cadastro()
    elif escolha == 2:
        listar()
    elif escolha == 3:
        buscar()
    elif escolha == 4:
        atualizar()
    elif escolha == 5:
        excluir()
    elif escolha == 0:
        break
    else:
        print("Erro! Digite um número válido.")
