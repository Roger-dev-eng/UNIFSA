package questao3;

public class VIP extends Ingresso {
    private double valorAdicional;

    public VIP(double valor, double valorAdicional) {
        super(valor);
        this.valorAdicional = valorAdicional;
    }

    public double getValorAdicional() {
        return valorAdicional;
    }

    public void setValorAdicional(double valorAdicional) {
        this.valorAdicional = valorAdicional;
    }

    public double getValorTotal() {
        return getValor() + valorAdicional;
    }

    @Override
    public void imprimeValor() {
        System.out.printf("\nValor do ingresso VIP: R$ %.2f (base: R$ %.2f + adicional: R$ %.2f)%n", 
            getValorTotal(), getValor(), valorAdicional);
    }
}
