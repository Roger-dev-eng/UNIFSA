package questao4;

public class Imovel {
    private String endereco;
    private double valorVenda;

    public Imovel(String endereco, double valorVenda) {
        this.endereco = endereco;
        this.valorVenda = valorVenda;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public double getValorVenda() {
        return valorVenda;
    }

    public void setValorVenda(double valorVenda) {
        this.valorVenda = valorVenda;
    }

    public double getValorFinal() {
        return valorVenda;
    }

    public void exibeInformacoes() {
        System.out.println("=== Informações do Imóvel ===");
        System.out.println("Endereço: " + endereco);
        System.out.printf("Valor de Venda: R$ %.2f%n", valorVenda);
        System.out.printf("Valor Final: R$ %.2f%n", getValorFinal());
    }
}
