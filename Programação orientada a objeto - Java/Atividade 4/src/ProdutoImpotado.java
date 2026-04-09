public class ProdutoImpotado extends Produto {
    private float taxaImportacao;

    public ProdutoImpotado(String nome, float preco, int estoque, String dataValidade, float taxaImportacao) {
        super(nome, preco, estoque);
        this.taxaImportacao = taxaImportacao;
    }

    public float getTaxaImportacao() {
        return taxaImportacao;
    }

    public void setTaxaImportacao(float taxaImportacao) {
        if (taxaImportacao > 0) {
            this.taxaImportacao = taxaImportacao;
        }
    }

    @Override
    public void vender(int qtd) {
        if (qtd <= 0) {
            System.out.println("Quantidade invalida para venda.");
            return;
        }

        if (getEstoque() >= qtd) {
            super.vender(qtd);
            System.out.println("Item importado vendido...");
            float precoFinal = getPreco() + (getPreco() * taxaImportacao);
            System.out.println("Preco final com taxa de importacao: " + precoFinal);
        } else {
            System.out.println("Estoque insuficiente para item importado.");
        }
    }    
}
