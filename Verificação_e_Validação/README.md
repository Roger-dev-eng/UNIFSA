# Investigação Conceitual

## 1. Paradigma xUnit vs. Asserções Idiomáticas

O JUnit e o unittest seguem o modelo xUnit, organizando os testes em classes e utilizando métodos específicos de asserção, como `assertEquals()` e `assertEqual()`. Já o Pytest permite criar testes diretamente em funções, utilizando o `assert` nativo do Python. Isso torna os testes mais simples, legíveis e facilita a identificação de falhas por meio da introspecção das asserções.

## 2. Ciclo de Vida e Gerenciamento de Estado de Testes

No JUnit 5, `@BeforeEach` prepara o ambiente antes de cada teste, enquanto `@AfterEach` realiza a limpeza após sua execução. No Pytest, as fixtures com `@pytest.fixture` desempenham funções equivalentes. O escopo `function` permite preparar recursos para cada teste, e o uso de `yield` possibilita executar a limpeza posteriormente.

## 3. Mecanismos de Dublês de Teste (Mocks & Stubs)

Mocks e stubs são utilizados para simular dependências externas, como bancos de dados e APIs, sem precisar estabelecer conexões reais. Ferramentas como Mockito e `unittest.mock` tornam os testes mais rápidos, previsíveis e independentes de serviços externos. Além disso, permitem simular diferentes comportamentos e falhas, facilitando a validação isolada das funcionalidades.

## 4. Análise de Cobertura: Cobertura de Linha vs. Cobertura de Ramos

A cobertura de linha (*Line Coverage*) mede a porcentagem de instruções executadas durante os testes. Já a cobertura de ramos (*Branch Coverage*) verifica se os diferentes caminhos de decisões condicionais foram executados, como os resultados verdadeiro e falso de um `if`. Portanto, atingir 100% de cobertura de linhas não garante que todos os caminhos lógicos tenham sido testados, pois algumas condições podem não ter sido verificadas em todas as possibilidades.
