public class Produto {
    private String nome;
    private float preco;
    private int estoque;

    public Produto (String nome, float preco, int estoque) {
        this.nome = nome;
        this.preco = preco;
        this.estoque = estoque;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        if (preco > 0) {
            this.preco = preco;
        }    
        else {
            System.out.println("Erro ao inserir valor negativo.");
        }
        
    } 

    public int getEstoque() {
        return estoque;
    }

    public void setEstoque(int estoque) {
        if (estoque >= 0) {
            this.estoque = estoque;
        }
        else {
            System.out.println("Erro ao inserir valor negativo.");
        }
    }

    public void adicionarEstoque(int qtd) {
        estoque += qtd;
    }

    public void vender(int qtd) {
        if (qtd <= 0) {
            System.out.println("Quantidade invalida para venda.");
            return;
        }

        if (qtd <= estoque) {
            estoque -= qtd;
            System.out.println("Vendido: " + qtd + " unidades.");
        } else {
            System.out.println("Estoque insuficiente.");
        }
    }

}
