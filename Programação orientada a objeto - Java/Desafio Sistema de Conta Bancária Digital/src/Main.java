import java.util.ArrayList;
import java.util.List;

import classes.ContaBancaria;
import classes.ContaCorrente;
import classes.ContaPoupanca;
import classes.ContaSalario;
import excecoes.OperacaoInvalidaException;
import excecoes.SaldoInsuficienteException;

public class Main {
    public static void main(String[] args) {
        List<ContaBancaria> contas = new ArrayList<ContaBancaria>();

        contas.add(new ContaCorrente(1, "Ana", 1000.0));
        contas.add(new ContaPoupanca(2, "Bruno", 2000.0));
        contas.add(new ContaSalario(3, "Carla", 1500.0));

        for (ContaBancaria conta : contas) {
            System.out.println("Rendimento: " + conta.calcularRendimento());
        }

        ContaBancaria contaSalario = contas.get(2);
        contaSalario.bloquear();
        try {
            contaSalario.depositar(100.0);
        } catch (OperacaoInvalidaException e) {
            System.out.println("Operacao invalida capturada: " + e.getMessage());
        }

        ContaBancaria contaCorrente = contas.get(0);
        try {
            contaCorrente.sacar(5000.0);
        } catch (OperacaoInvalidaException e) {
            System.out.println("Operacao invalida capturada: " + e.getMessage());
        } catch (SaldoInsuficienteException e) {
            System.out.println("Saldo insuficiente capturada: " + e.getMessage());
        }
    }
}
