# Sistema de Simulação de Veículos

## Descrição
Sistema em Java que simula o comportamento de diferentes tipos de veículos em uma pista de tráfego, implementando conceitos avançados de Programação Orientada a Objeto como herança, polimorfismo, encapsulamento e identidade de objetos.

## Principais Classes e Seus Atributos

### Veiculo (Classe Base)
Representa um veículo genérico no sistema.

Atributos:
- marca: String - marca do veículo
- modelo: String - modelo do veículo
- velocidade: double - velocidade atual em km/h
- placa: String - identificador único do veículo
- combustivel: double - quantidade de combustível em litros
- tanqueCapacidade: double - capacidade máxima do tanque (60L)
- tipoCombustivel: String - tipo de combustível (Gasolina, Diesel)

### Moto (extends Veiculo)
Representa uma motocicleta.

Atributos:
- cilindradas: double - cilindradas do motor

### Carro (extends Veiculo)
Representa um carro/automóvel.

Atributos:
- numeroPortas: int - quantidade de portas do carro

### Caminhao (extends Veiculo)
Representa um caminhão.

Atributos:
- capacidadeCarga: double - capacidade máxima de carga em kg
- cargaAtual: double - carga atual transportada em kg

### SimuladorDeTrafego
Gerencia a simulação de tráfego em uma pista.

Atributos:
- veiculosNaPista: List<Veiculo> - lista de veículos na pista
- velocidadeMaximaPista: double - velocidade máxima permitida
- condicoes: String - condições da pista (Normal, Chuva, Névoa)
- CAPACIDADE_MAXIMA_PISTA: int - limite de 50 veículos

### Garagem
Armazena e gerencia veículos.

Atributos:
- veiculos: List<Veiculo> - lista de veículos armazenados

## Principais Métodos

### Veiculo
- acelerar() - aumenta velocidade em 10 km/h
- acelerar(int incremento) - aumenta velocidade pelo valor especificado
- frear() - reduz velocidade em 10 km/h
- frear(int incremento) - reduz velocidade pelo valor especificado
- ligar() - liga o veículo
- abastecer(double litros) - abastece com combustível padrão
- abastecer(double litros, String tipoCombustivel) - abastece com tipo especificado
- exibirStatus() - exibe informações do veículo
- calcularConsumo(double distancia) - calcula consumo estimado
- interagir(Veiculo outro) - simula interação entre veículos
- interagir(Caminhao c) - simula interação específica com caminhão
- equals(Object obj) - compara veículos pela placa
- hashCode() - retorna hash baseado na placa

### Moto
- acelerar() - acelera +15 km/h (sobrescrito)
- calcularConsumo(double distancia) - retorna distancia/20 (20 km/L)
- exibirStatus() - exibe status incluindo cilindradas (sobrescrito)
- ligar(String modo) - liga em modo específico (Esportivo, etc)

### Carro
- calcularConsumo(double distancia) - retorna distancia/12 (12 km/L) (sobrescrito)
- exibirStatus() - exibe status incluindo portas (sobrescrito)
- ligar(String modo) - liga em modo específico (Econômico, etc)

### Caminhao
- acelerar() - acelera +8 km/h (vazio) ou +5 km/h (carregado) (sobrescrito)
- calcularConsumo(double distancia) - retorna distancia/4 com acréscimo de 10% se carregado (sobrescrito)
- exibirStatus() - exibe status incluindo carga (sobrescrito)
- ligar(String modo) - liga em modo específico (Carga Pesada, etc)
- carregarCarga(double quantidade) - adiciona carga ao caminhão
- descarregarCarga(double quantidade) - remove carga do caminhão

### SimuladorDeTrafego
- adicionarVeiculoPista(Veiculo veiculo) - adiciona veículo à pista (máx 50)
- removerVeiculoPista(Veiculo veiculo) - remove veículo da pista
- ligarTodoVeiculos() - liga todos os veículos
- acelerarTodos() - acelera todos os veículos
- frearTodos() - freia todos os veículos
- exibirStatusTrafego() - exibe status de todos na pista
- simularInteracao(int indice1, int indice2) - simula interação entre dois veículos
- alterarCondicoes(String novasCondicoes) - muda condições da pista

### Garagem
- adicionarVeiculo(Veiculo veiculo) - adiciona veículo à garagem
- listarVeiculo() - lista todos os veículos
- buscarPorPlaca(String placa) - busca veículo pela placa
- estacionar(Carro c) - estaciona um carro
- estacionar(Moto m) - estaciona uma moto
- estacionar(Caminhao c) - estaciona um caminhão

## Hierarquia de Classes

```
Veiculo (classe base abstrata)
├── Moto
├── Carro
└── Caminhao

SimuladorDeTrafego (composição com List<Veiculo>)
Garagem (composição com List<Veiculo>)
```

## Sobrescrita de Métodos

### acelerar()
- Veiculo: velocidade += 10
- Moto: velocidade += 15
- Caminhao: velocidade += 8 (vazio) ou 5 (carregado)

### calcularConsumo(double distancia)
- Veiculo: distancia / 10 (10 km/L padrão)
- Moto: distancia / 20 (20 km/L - eficiente)
- Carro: distancia / 12 (12 km/L - moderado)
- Caminhao: (distancia / 4) * 1.1 se carregado (4 km/L com acréscimo)

### exibirStatus()
- Veiculo: exibe marca, modelo, placa, velocidade, combustível
- Moto: adiciona cilindradas
- Carro: adiciona número de portas
- Caminhao: adiciona carga atual e capacidade

## Sobrecarga de Métodos

### acelerar()
- acelerar() - acelera quantidade padrão
- acelerar(int incremento) - acelera quantidade específica

### frear()
- frear() - freia quantidade padrão (10 km/h)
- frear(int incremento) - freia quantidade específica

### abastecer()
- abastecer(double litros) - abastece com combustível padrão (Gasolina)
- abastecer(double litros, String tipoCombustivel) - abastece com tipo especificado

### interagir()
- interagir(Veiculo outro) - interação genérica entre veículos
- interagir(Caminhao c) - interação específica com caminhão

### ligar()
- ligar() - liga sem parâmetros (Veiculo)
- ligar(String modo) - liga em modo específico (Moto, Carro, Caminhao)

### estacionar()
- estacionar(Carro c) - estaciona carro
- estacionar(Moto m) - estaciona moto
- estacionar(Caminhao c) - estaciona caminhão

## Tratamento de Erros

O sistema implementa validações robustas:
- Pista lotada (máximo 50 veículos)
- Prevenção de duplicatas por placa
- Validação de combustível antes de acelerar
- Validação de capacidade de tanque
- Tratamento de parâmetros inválidos
- Mensagens de erro claras
