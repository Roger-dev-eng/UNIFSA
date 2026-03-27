# Sistema Academico

Projeto simples de Programacao Orientada a Objetos em Java para modelar:

- alunos de graduacao e pos-graduacao;
- professores efetivos e temporarios;
- disciplina com professor responsavel e lista de alunos.

## Estrutura

- `src/Main.java`: ponto de entrada com exemplos de uso das classes.
- `src/Classes/Aluno.java`: classe abstrata base de aluno.
- `src/Classes/AlunoGraduacao.java`: regra de aprovacao da graduacao (nota >= 7).
- `src/Classes/AlunoPosGraduacao.java`: regra de aprovacao da pos-graduacao (nota >= 6).
- `src/Classes/Professor.java`: classe base de professor.
- `src/Classes/ProfessorEfetivo.java`: tipo de professor efetivo.
- `src/Classes/ProfessorTemporario.java`: tipo de professor temporario com duracao de contrato.
- `src/Classes/Disciplina.java`: disciplina com professor e colecao de alunos.

## Conceitos de POO usados

- **Encapsulamento**: atributos privados com getters e setters.
- **Heranca**: `AlunoGraduacao` e `AlunoPosGraduacao` herdam de `Aluno`; `ProfessorEfetivo` e `ProfessorTemporario` herdam de `Professor`.
- **Polimorfismo**: `verificarAprovacao()` e sobrescrita de `aumentarSalario()` em `ProfessorTemporario`.
- **Abstracao**: `Aluno` e abstrata e define contrato para aprovacao.

## Regras e validacoes

- Nota final deve estar entre `0` e `10`.
- Duracao de contrato do professor temporario nao pode ser negativa.
- Toda disciplina deve ter um professor valido.
- Aluno duplicado na mesma disciplina nao e adicionado novamente.

## Como compilar e executar

No diretorio raiz do projeto (`Sistema academico`):

```bash
javac src/Main.java src/Classes/*.java
java -cp src Main
```

## Saida esperada (resumo)

O `Main` demonstra:

- cadastro de alunos e verificacao de aprovacao;
- ajuste de nota;
- criacao de professores;
- associacao de professor/alunos a uma disciplina;
- exibicao de dados basicos no console.
