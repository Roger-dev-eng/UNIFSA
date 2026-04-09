public class Supermercado {
    public static void main(String[] args) {
        Produto arroz = new Produto("Arroz", 25.0f, 40);
        Perecivel leite = new Perecivel("Leite", 6.5f, 20, "15/05/2026");
        ProdutoImpotado azeite = new ProdutoImpotado("Azeite", 32.0f, 12, "", 0.20f);

        Venda venda = new Venda();

        venda.processarItem(arroz, 3);
        venda.processarItem(leite, 2);
        venda.processarItem(azeite, 1);
    }
}
