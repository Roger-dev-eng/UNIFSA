# Os testes verificam os limites de quantidade, a taxa de defeitos, as exceções e casos adicionais.
import pytest
from validador import validar_lote


@pytest.mark.parametrize("q, t, esperado", [
    (100, 2.5, True),     # Limite inferior. Altere essa linha para `(100, 2.5, False)` para demostrar que os teste conseguem detectar um erro.
    (5000, 0.0, True),    # Limite superior
    (99, 1.0, False),     # Quantidade abaixo
    (5001, 1.0, False),   # Quantidade acima
    (500, 2.6, False),    # Taxa excedida
    (500, 2.5, True),     # Taxa no limite
    (1000, 1.0, True),    # Lote aprovado
])
def test_lotes(q, t, esperado):
    assert validar_lote(q, t) == esperado


@pytest.mark.parametrize("q, t", [
    (0, 1.0),
    (-1, 1.0),
    (150, -0.1),
])
def test_excecoes(q, t):
    with pytest.raises(ValueError):
        validar_lote(q, t)

# Execute os teste com `pytest -v` no terminal para ver os resultados detalhados.
