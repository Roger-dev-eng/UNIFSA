package questao4;

public class Main {
    public static void main(String[] args) {

        ImoveNovoComAcrescimo imovel1 = new ImoveNovoComAcrescimo("Rua das Flores, 100 - Centro", 500000.0, 75000.0);
        ImoveAntigoComDesconto imovel2 = new ImoveAntigoComDesconto("Avenida Principal, 250 - Zona Leste", 300000.0, 15.0);
        ImoveNovoComAcrescimo imovel3 = new ImoveNovoComAcrescimo("Rua Moderna, 50 - Bairro Nobre", 800000.0, 120000.0);
        ImoveAntigoComDesconto imovel4 = new ImoveAntigoComDesconto("Rua Antiga, 333 - Zona Oeste", 200000.0, 25.0);
        Imovel imovel5 = new Imovel("Rua Simples, 777 - Bairro Residencial", 400000.0);

        imovel1.exibeInformacoes();
        imovel2.exibeInformacoes();
        imovel3.exibeInformacoes();
        imovel4.exibeInformacoes();
        imovel5.exibeInformacoes();
    }
}