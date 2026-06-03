package classes;

import excecoes.OperacaoInvalidaException;
import excecoes.SaldoInsuficienteException;
import interfaces.Tributavel;

public class ContaCorrente extends ContaBancaria implements Tributavel {
    private static final double LIMITE_CHEQUE_ESPECIAL = 1000.0;
    private static final double TAXA_IMPOSTO_SAQUE = 0.0038;

    public ContaCorrente(int numero, String titular, double saldo) {
        super(numero, titular, saldo);
    }

    @Override
    public void sacar(double valor) throws OperacaoInvalidaException {
        validarContaAtiva();
        if (valor <= 0) {
            throw new IllegalArgumentException("Valor de saque deve ser positivo.");
        }
        double saldoDisponivel = getSaldo() + LIMITE_CHEQUE_ESPECIAL;
        if (saldoDisponivel < valor) {
            throw new SaldoInsuficienteException(getSaldo(), valor);
        }
        double imposto = valor * TAXA_IMPOSTO_SAQUE;
        setSaldo(getSaldo() - valor - imposto);
    }

    @Override
    public double calcularRendimento() {
        return 0.0;
    }

    @Override
    public double calcularImposto() {
        return 0.0;
    }
}
