public class Venda {
    public void processarItem(Produto p, int quantidade) {
        if (p == null) {
            System.out.println("Produto inválido.");
            return;
        }

        if (quantidade <= 0) {
            System.out.println("Quantidade inválida.");
            return;
        }

        float valorTotal = quantidade * p.getPreco();

        System.out.println("Produto: " + p.getNome());
        System.out.println("Quantidade: " + quantidade);
        System.out.println("Preco unitario: " + p.getPreco());
        System.out.println("Total: " + valorTotal);

        p.vender(quantidade);
        System.out.println("----------------");
    }

    public void processarltem(Produto p, int quantidade) {
        processarItem(p, quantidade);
    }
}
