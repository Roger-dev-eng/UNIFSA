alunos = {}

nomes_cadastrados = set()

def menu():
    print("\n==============================")
    print("SISTEMA DE CONTROLE DE ALUNOS")
    print("==============================")
    print("1 - Cadastrar aluno")
    print("2 - Registrar notas")
    print("3 - Listar alunos e médias")
    print("4 - Buscar aluno")
    print("5 - Mostrar aprovados e reprovados")
    print("6 - Relatórios")
    print("0 - Sair")

def cadastrar_aluno():
    matricula = input("Digite a matrícula do aluno: ").strip()
    if matricula in alunos:
        print("Matrícula já cadastrada.")
        return

    nome = input("Digite o nome do aluno: ").strip()
    if nome in nomes_cadastrados:
        print("Aluno já cadastrado.")
        return

    alunos[matricula] = (nome, ())
    nomes_cadastrados.add(nome)
    print("Aluno cadastrado com sucesso.")

def registrar_notas():
    matricula = input("Digite a matrícula do aluno: ").strip()
    if matricula not in alunos:
        print("Aluno não encontrado.")
        return

    nome, _ = alunos[matricula]
    notas_temp = []

    while True:
        try:
            nota = float(input("Digite uma nota (ou -1 para encerrar): "))
            if nota == -1:
                break
            if 0 <= nota <= 10:
                notas_temp.append(nota)
            else:
                print("A nota deve estar entre 0 e 10.")
        except ValueError:
            print("Entrada inválida, digite um número.")

    alunos[matricula] = (nome, tuple(notas_temp))
    print("Notas registradas com sucesso.")

def listar_alunos_medias():
    if not alunos:
        print("Nenhum aluno cadastrado.")
        return

    for matricula, (nome, notas) in alunos.items():
        if notas:
            media = sum(notas) / len(notas)
            print(f"Matrícula: {matricula} | Nome: {nome} | Média: {media:.2f}")
        else:
            print(f"Matrícula: {matricula} | Nome: {nome} | Sem notas registradas.")

def buscar_aluno():
    termo = input("Digite o nome ou matrícula do aluno: ").strip()
    encontrado = False

    for matricula, (nome, notas) in alunos.items():
        if termo.lower() == nome.lower() or termo == matricula:
            media = sum(notas) / len(notas) if notas else 0
            print(f"Matrícula: {matricula}")
            print(f"Nome: {nome}")
            print(f"Notas: {notas if notas else 'Sem notas'}")
            print(f"Média: {media:.2f}")
            encontrado = True
            break

    if not encontrado:
        print("Aluno não encontrado.")

def mostrar_aprovados_reprovados():
    aprovados = []
    reprovados = []

    for matricula, (nome, notas) in alunos.items():
        if notas:
            media = sum(notas) / len(notas)
            if media >= 7:
                aprovados.append((nome, media))
            else:
                reprovados.append((nome, media))

    print("\nAlunos Aprovados:")
    for nome, media in aprovados:
        print(f"{nome} - Média: {media:.2f}")

    print("\nAlunos Reprovados:")
    for nome, media in reprovados:
        print(f"{nome} - Média: {media:.2f}")

def relatorios():
    print("\nRelatórios disponíveis:")
    print("1 - Alunos cadastrados")
    print("2 - Médias individuais")
    print("3 - Aprovados e reprovados")
    opcao = input("Escolha uma opção: ")

    if opcao == "1":
        print("\nAlunos cadastrados:")
        for matricula, (nome, _) in alunos.items():
            print(f"Matrícula: {matricula} | Nome: {nome}")

    elif opcao == "2":
        listar_alunos_medias()

    elif opcao == "3":
        mostrar_aprovados_reprovados()

    else:
        print("Opção inválida.")

# Programa principal
while True:
    menu()
    opcao = input("Escolha uma opção: ").strip()

    if opcao == "1":
        cadastrar_aluno()
    elif opcao == "2":
        registrar_notas()
    elif opcao == "3":
        listar_alunos_medias()
    elif opcao == "4":
        buscar_aluno()
    elif opcao == "5":
        mostrar_aprovados_reprovados()
    elif opcao == "6":
        relatorios()
    elif opcao == "0":
        print("Encerrando o sistema.")
        break
    else:
        print("Opção inválida, tente novamente.")