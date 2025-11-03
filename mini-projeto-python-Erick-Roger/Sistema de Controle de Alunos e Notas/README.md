#  Sistema de Controle de Alunos

##  Descrição

O **Sistema de Controle de Alunos** é um programa simples em **Python** que permite o gerenciamento de informações de estudantes, incluindo cadastro, registro de notas, cálculo de médias e geração de relatórios.

Esse sistema utiliza estruturas de dados nativas do Python (como dicionários, tuplas e conjuntos) para armazenar e manipular as informações dos alunos de forma dinâmica e eficiente.

---

##  Funcionalidades

### 1. Cadastrar aluno
Permite registrar um novo aluno informando sua **matrícula** e **nome**.  
- Impede o cadastro de duas matrículas ou nomes iguais.  
- Armazena os dados em um dicionário global `alunos`.

### 2. Registrar notas
Permite inserir notas para um aluno já cadastrado.  
- As notas devem estar entre **0 e 10**.  
- O usuário pode inserir várias notas, encerrando com `-1`.

### 3. Listar alunos e médias
Exibe todos os alunos cadastrados, juntamente com suas **médias** (caso possuam notas registradas).

### 4. Buscar aluno
Permite localizar um aluno pelo **nome** ou **matrícula**, exibindo:
- Nome  
- Matrícula  
- Notas registradas  
- Média

### 5. Mostrar aprovados e reprovados
Lista todos os alunos classificados como:
- **Aprovado:** média maior ou igual a 7.0  
- **Reprovado:** média menor que 7.0  

### 6. Relatórios
Gera três tipos de relatórios:
1. Alunos cadastrados  
2. Médias individuais  
3. Aprovados e reprovados  

### 0. Sair
Encerra o programa de forma segura.

---

##  Estrutura de Dados Utilizada

- **Dicionário (`alunos`)**  
  Cada aluno é armazenado com a **matrícula** como chave e uma tupla contendo `(nome, notas)` como valor.

  ```python
  alunos = {
      "123": ("Maria", (8.5, 7.0, 9.0)),
      "456": ("João", (6.0, 5.5))
  } 
  ```
- **Conjunto (`nomes_cadastrados`)**\
Evita o cadastro duplicado de alunos com o mesmo nome.

## Exemplos de Uso
### Exemplo 1 — Cadastro e listagem de alunos

Entrada:
```python
1  # Escolher opção "Cadastrar aluno"
Digite a matrícula do aluno: 1001
Digite o nome do aluno: Ana Silva

1  # Cadastrar outro aluno
Digite a matrícula do aluno: 1002
Digite o nome do aluno: Bruno Costa

3  # Listar alunos e médias
```

Saída esperada:
```python
Matrícula: 1001 | Nome: Ana Silva | Sem notas registradas.
Matrícula: 1002 | Nome: Bruno Costa | Sem notas registradas.
```