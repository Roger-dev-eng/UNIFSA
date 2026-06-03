package excecoes;

public class SaldoInsuficienteException extends RuntimeException {
    private final double saldo;
    private final double valorSolicitado;

    public SaldoInsuficienteException(double saldo, double valorSolicitado) {
        super("Saldo insuficiente: saldo=" + saldo + ", valor=" + valorSolicitado + ".");
        this.saldo = saldo;
        this.valorSolicitado = valorSolicitado;
    }

    public SaldoInsuficienteException(String message, double saldo, double valorSolicitado) {
        super(message);
        this.saldo = saldo;
        this.valorSolicitado = valorSolicitado;
    }

    public double getSaldo() {
        return saldo;
    }

    public double getValorSolicitado() {
        return valorSolicitado;
    }
}
