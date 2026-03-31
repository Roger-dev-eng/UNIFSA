package questao4;

public class ImoveNovoComAcrescimo extends Imovel {
    private double valorAcrescimo;

    public ImoveNovoComAcrescimo(String endereco, double valorVenda, double valorAcrescimo) {
        super(endereco, valorVenda);
        this.valorAcrescimo = valorAcrescimo;
    }

    public double getValorAcrescimo() {
        return valorAcrescimo;
    }

    public void setValorAcrescimo(double valorAcrescimo) {
        this.valorAcrescimo = valorAcrescimo;
    }

    @Override
    public double getValorFinal() {
        return getValorVenda() + valorAcrescimo;
    }

    @Override
    public void exibeInformacoes() {
        System.out.println("=== Imóvel Novo com Acréscimo ===");
        System.out.println("Endereço: " + getEndereco());
        System.out.printf("Valor Base: R$ %.2f%n", getValorVenda());
        System.out.printf("Acréscimo: R$ %.2f%n", valorAcrescimo);
        System.out.printf("Valor Final: R$ %.2f%n", getValorFinal());
        System.out.println();
    }
}
