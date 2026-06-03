package classes;

import excecoes.OperacaoInvalidaException;
import interfaces.Tributavel;

public class ContaPoupanca extends ContaBancaria implements Tributavel {
    private static final double TAXA_RENDIMENTO_MENSAL = 0.005;
    private static final double TAXA_IMPOSTO_RENDIMENTO = 0.225;
    private int diasDesdeUltimoSaque;

    public ContaPoupanca(int numero, String titular, double saldo) {
        super(numero, titular, saldo);
        this.diasDesdeUltimoSaque = 30;
    }

    @Override
    public double calcularRendimento() {
        return getSaldo() * TAXA_RENDIMENTO_MENSAL;
    }

    @Override
    public double calcularImposto() {
        return calcularRendimento() * TAXA_IMPOSTO_RENDIMENTO;
    }

    @Override
    public void sacar(double valor) throws OperacaoInvalidaException {
        validarContaAtiva();
        if (diasDesdeUltimoSaque < 30) {
            throw new OperacaoInvalidaException("saque antes de 30 dias");
        }
        super.sacar(valor);
        diasDesdeUltimoSaque = 0;
    }

    public void avancarDias(int dias) {
        if (dias < 0) {
            throw new IllegalArgumentException("Dias deve ser positivo.");
        }
        diasDesdeUltimoSaque += dias;
    }
}
