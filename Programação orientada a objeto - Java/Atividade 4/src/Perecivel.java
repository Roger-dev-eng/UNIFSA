public class Perecivel extends Produto {
    private String dataValidade;

    public Perecivel(String nome, float preco, int estoque, String dataValidade) {
        super(nome, preco, estoque);
        this.dataValidade = dataValidade;
    }

    public String getDataValidade() {
        return dataValidade;
    }

    public void setDataValidade(String dataValidade) {
        this.dataValidade = dataValidade;
    }

    @Override
    public void vender(int qtd) {
        if (qtd <= 0) {
            System.out.println("Quantidade invalida para venda.");
            return;
        }

        System.out.println("Verificado validade...");
        if (getEstoque() >= qtd) {
            setEstoque(getEstoque() - qtd);
            System.out.println("Item perecível vendido...");
        } else {
            System.out.println("Estoque insuficiente para item perecivel.");
        }
    }
}
