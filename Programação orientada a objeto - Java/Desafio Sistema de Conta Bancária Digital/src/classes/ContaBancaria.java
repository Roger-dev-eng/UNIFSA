package classes;

import excecoes.OperacaoInvalidaException;
import excecoes.SaldoInsuficienteException;
import interfaces.Bloqueavel;

public abstract class ContaBancaria implements Bloqueavel {
    private final int numero;
    private final String titular;
    private double saldo;
    private boolean ativa = true;

    public ContaBancaria(int numero, String titular, double saldo) {
        if (titular == null || titular.trim().isEmpty()) {
            throw new IllegalArgumentException("Titular nao pode ser nulo ou vazio.");
        }
        this.numero = numero;
        this.titular = titular;
        this.saldo = saldo;
    }

    public int getNumero() {
        return numero;
    }

    public String getTitular() {
        return titular;
    }

    public double getSaldo() {
        return saldo;
    }

    protected void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    protected void validarContaAtiva() throws OperacaoInvalidaException {
        if (!ativa) {
            throw new OperacaoInvalidaException("conta bloqueada");
        }
    }

    public void depositar(double valor) throws OperacaoInvalidaException {
        validarContaAtiva();
        if (valor <= 0) {
            throw new IllegalArgumentException("Valor de deposito deve ser positivo.");
        }
        saldo += valor;
    }

    public void sacar(double valor) throws OperacaoInvalidaException {
        validarContaAtiva();
        if (valor <= 0) {
            throw new IllegalArgumentException("Valor de saque deve ser positivo.");
        }
        if (saldo < valor) {
            throw new SaldoInsuficienteException(saldo, valor);
        }
        saldo -= valor;
    }

    public void bloquear() {
        ativa = false;
    }

    public void desbloquear() {
        ativa = true;
    }

    public boolean isAtiva() {
        return ativa;
    }

    public abstract double calcularRendimento();
}
