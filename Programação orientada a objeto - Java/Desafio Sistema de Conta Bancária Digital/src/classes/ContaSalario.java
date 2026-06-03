package classes;

import excecoes.OperacaoInvalidaException;

public class ContaSalario extends ContaBancaria {
    private int saquesNoMes;

    public ContaSalario(int numero, String titular, double saldo) {
        super(numero, titular, saldo);
        this.saquesNoMes = 0;
    }

    @Override
    public double calcularRendimento() {
        return 0.0;
    }

    @Override
    public void sacar(double valor) throws OperacaoInvalidaException {
        validarContaAtiva();
        if (saquesNoMes >= 1) {
            throw new OperacaoInvalidaException("maximo de 1 saque por mes");
        }
        super.sacar(valor);
        saquesNoMes += 1;
    }

    public void novoMes() {
        saquesNoMes = 0;
    }
}
