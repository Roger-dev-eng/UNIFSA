# Este código implementa as três regras do roteiro: aprovação, reprovação e exceção para parâmetros inválidos.
def validar_lote(qtd: int, taxa: float) -> bool:
    if qtd <= 0 or taxa < 0.0:
        raise ValueError("Parametros invalidos")

    return 100 <= qtd <= 5000 and taxa <= 2.5
