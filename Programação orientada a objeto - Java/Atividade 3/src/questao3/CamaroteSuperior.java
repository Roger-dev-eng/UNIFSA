package questao3;

public class CamaroteSuperior extends VIP {
    private double valorAdicionalSuperior;

    public CamaroteSuperior(double valor, double valorAdicionalVIP, double valorAdicionalSuperior) {
        super(valor, valorAdicionalVIP);
        this.valorAdicionalSuperior = valorAdicionalSuperior;
    }

    public double getValorAdicionalSuperior() {
        return valorAdicionalSuperior;
    }

    public void setValorAdicionalSuperior(double valorAdicionalSuperior) {
        this.valorAdicionalSuperior = valorAdicionalSuperior;
    }

    public double getValorTotal() {
        return getValor() + getValorAdicional() + valorAdicionalSuperior;
    }

    @Override
    public void imprimeValor() {
        System.out.printf("\nValor Total do Camarote Superior: R$ %.2f (base: R$ %.2f + VIP: R$ %.2f + superior: R$ %.2f)%n", 
            getValorTotal(), getValor(), getValorAdicional(), valorAdicionalSuperior);
    }
}
