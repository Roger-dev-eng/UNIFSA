# Sistema de Entregas

Projeto da 3ª avaliação de Estrutura de Dados. O programa em C cadastra pedidos, consulta o cadastro, organiza o despacho e calcula distâncias no mapa de bairros.

## Compilar e executar

Execute os comandos na raiz do projeto, onde está este README. Para montar o menu, compile os seis arquivos `.c` diretamente em `src/`.

### Windows com Visual Studio

Abra o **Developer PowerShell for Visual Studio**, mude para a pasta do projeto e execute o seguinte.

```powershell
$fontes = (Get-ChildItem ./src/*.c).FullName
cl /nologo /std:c11 /utf-8 /W4 /O2 @fontes /Fe:entregas.exe
.\entregas.exe
```

### Windows com MinGW

No PowerShell, com o GCC do MinGW disponível no terminal, execute o seguinte.

```powershell
$fontes = (Get-ChildItem ./src/*.c).FullName
gcc -std=c11 -O2 -Wall -Wextra -Wpedantic @fontes -o entregas.exe
.\entregas.exe
```

### Linux

```sh
cc -std=c11 -O2 -Wall -Wextra -Wpedantic ./src/*.c -o entregas
./entregas
```

## Usar o menu

1. **Cadastrar pedido** pede um código inteiro positivo e único, uma data no formato `AAAA-MM-DD`, a indicação de urgência e o bairro de destino.
2. **Buscar pedido por código** mostra os dados do pedido e sua situação atual.
3. **Listar pedidos por data** mostra os pedidos da data mais antiga para a mais recente. Em datas iguais, o menor código aparece primeiro.
4. **Despachar próximo pedido** atende os urgentes primeiro. Entre pedidos com a mesma prioridade, sai quem chegou antes.
5. **Desfazer despacho** devolve o último pedido despachado à fila com a prioridade e a ordem de chegada originais.
6. **Refazer despacho** despacha novamente o pedido correspondente à última operação desfeita. Ao fazer um novo despacho pelo item 4, o histórico de operações disponíveis para refazer é limpo.
7. **Consultar menor número de ruas** mede a distância entre dois bairros pelo mapa de exemplo.
8. Use **0** para sair.

Para conferir o desempate por chegada, cadastre um pedido normal seguido de dois urgentes, com códigos diferentes. Ao despachar duas vezes, sairão os urgentes na ordem em que foram cadastrados. Para testar a rota, escolha Leste (4) como origem e Oeste (5) como destino. O resultado esperado é **4 ruas**. O cadastro começa vazio e os dados ficam em memória até o programa encerrar.

## Estrutura do código

Os módulos abaixo ficam em `src/`.

| Demanda | Arquivos | Estrutura e operação |
| --- | --- | --- |
| D1 — Busca | `d1_busca.h` e `d1_busca.c` | Tabela de dispersão com sondagem linear. |
| D2 — Listagem | `d2_listagem.h` e `d2_listagem.c` | Vetor dinâmico. O mergesort ordena uma cópia por data e código. |
| D3 — Despacho | `d3_despacho.h` e `d3_despacho.c` | Heap binário com urgência e ordem de chegada. |
| D4 — Histórico | `d4_historico.h` e `d4_historico.c` | Duas pilhas, uma para operações feitas e outra para desfeitas. |
| D5 — Rotas | `d5_rotas.h` e `d5_rotas.c` | Grafo com listas de adjacência e busca em largura. |

[`src/main.c`](src/main.c) cria as estruturas e integra suas operações ao menu.

## Por que escolhemos essas estruturas

Os tempos abaixo são as medianas do teste com 10.000 registros em entrada aleatória.

- **D1 — Tabela de dispersão**. O código do pedido indica onde começar a busca. A consulta levou **1,10 µs** na tabela e **4,08 µs** na busca linear em vetor.
- **D2 — Vetor com mergesort**. Ordenar uma cópia na hora de listar favorece cadastros frequentes. Inserir levou **0,0083 µs** por pedido, ante **0,6081 µs** no vetor sempre ordenado. A listagem completa custou **1.360 µs**, ante **15,5 µs** na alternativa.
- **D3 — Heap binário**. O próximo pedido fica na raiz, seguindo a urgência e a ordem de chegada. Cada despacho levou **0,1048 µs** no heap e **9,4560 µs** no vetor com varredura.
- **D4 — Duas pilhas**. Cada pilha dá acesso direto ao último despacho feito ou desfeito. A escolha deixa essa lógica explícita. No teste isolado do histórico, desfazer ou refazer levou **0,0070 µs** por ação nas pilhas e **0,0018 µs** no vetor com cursor.
- **D5 — Grafo com listas de adjacência**. A busca em largura percorre as ruas que ligam cada bairro aos seus vizinhos. Com 10.000 bairros, a consulta levou **127,5 µs** com listas e **22.936,7 µs** com matriz de adjacência.

## Relatório

Para mais detalhes sobre as alternativas, o método e os resultados com 100, 1.000 e 10.000 registros, consulte o [Relatório de decisões de projeto](relatorio-decisoes-entregas.pdf). O relatório inclui entradas aleatórias, ordenadas e quase ordenadas. As medições usam os TADs do projeto sem a entrada e a saída do menu.

### Repetir as medições no Windows

O [código do benchmark](src/medicoes/benchmark.c) e o [CSV usado no relatório](src/medicoes/medicoes.csv) ficam em `src/medicoes/`. O benchmark tem seu próprio `main()` e é compilado junto dos cinco módulos. Para gerar uma nova medição, use estes comandos na raiz do projeto, pelo **Developer PowerShell for Visual Studio**.

```powershell
$modulos = (Get-ChildItem ./src/d*.c).FullName
cl /nologo /std:c11 /utf-8 /W4 /O2 /I src ./src/medicoes/benchmark.c @modulos /Fe:benchmark.exe
.\benchmark.exe | Set-Content -Encoding utf8 medicoes-novas.csv
```
