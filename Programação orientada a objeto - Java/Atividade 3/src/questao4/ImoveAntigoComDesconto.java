package questao04;

public class ImoveAntigoComDesconto extends Imovel {
    private double percentualDesconto;

    public ImoveAntigoComDesconto(String endereco, double valorVenda, double percentualDesconto) {
        super(endereco, valorVenda);
        this.percentualDesconto = percentualDesconto;
    }

    public double getPercentualDesconto() {
        return percentualDesconto;
    }

    public void setPercentualDesconto(double percentualDesconto) {
        this.percentualDesconto = percentualDesconto;
    }

    public double getValorDesconto() {
        return getValorVenda() * (percentualDesconto / 100);
    }

    @Override
    public double getValorFinal() {
        return getValorVenda() - getValorDesconto();
    }

    @Override
    public void exibeInformacoes() {
        System.out.println("=== Imóvel Antigo com Desconto ===");
        System.out.println("Endereço: " + getEndereco());
        System.out.printf("Valor Base: R$ %.2f%n", getValorVenda());
        System.out.printf("Percentual de Desconto: %.1f%%%n", percentualDesconto);
        System.out.printf("Valor do Desconto: R$ %.2f%n", getValorDesconto());
        System.out.printf("Valor Final: R$ %.2f%n", getValorFinal());
        System.out.println();
    }
}
