package questao04;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("====================================");
        System.out.println("   SISTEMA DE GERENCIAMENTO DE IMÓVEIS");
        System.out.println("====================================\n");

        // Criando uma lista de imóveis
        List<Imovel> imoveis = new ArrayList<>();

        // Adicionando imóveis à lista
        imoveis.add(new ImoveNovoComAcrescimo("Rua das Flores, 100 - Centro", 500000.0, 75000.0));
        imoveis.add(new ImoveAntigoComDesconto("Avenida Principal, 250 - Zona Leste", 300000.0, 15.0));
        imoveis.add(new ImoveNovoComAcrescimo("Rua Moderna, 50 - Bairro Nobre", 800000.0, 120000.0));
        imoveis.add(new ImoveAntigoComDesconto("Rua Antiga, 333 - Zona Oeste", 200000.0, 25.0));
        imoveis.add(new Imovel("Rua Simples, 777 - Bairro Residencial", 400000.0));

        // Exibindo informações de todos os imóveis
        System.out.println("--- LISTAGEM DE IMÓVEIS ---\n");
        for (Imovel imovel : imoveis) {
            imovel.exibeInformacoes();
        }

        // Calculando valor total do portfólio
        System.out.println("====================================");
        System.out.println("   RESUMO DO PORTFÓLIO");
        System.out.println("====================================\n");

        double valorTotalBase = 0;
        double valorTotalFinal = 0;

        for (Imovel imovel : imoveis) {
            valorTotalBase += imovel.getValorVenda();
            valorTotalFinal += imovel.getValorFinal();
        }

        System.out.printf("Quantidade de Imóveis: %d%n", imoveis.size());
        System.out.printf("Valor Total (Base): R$ %.2f%n", valorTotalBase);
        System.out.printf("Valor Total (Final): R$ %.2f%n", valorTotalFinal);
        System.out.printf("Diferença: R$ %.2f%n", valorTotalFinal - valorTotalBase);

        // Estatísticas
        System.out.println("\n--- ESTATÍSTICAS ---\n");
        long imoveisNovos = imoveis.stream()
            .filter(i -> i instanceof ImoveNovoComAcrescimo)
            .count();
        long imoveisAntigos = imoveis.stream()
            .filter(i -> i instanceof ImoveAntigoComDesconto)
            .count();

        System.out.printf("Imóveis Novos com Acréscimo: %d%n", imoveisNovos);
        System.out.printf("Imóveis Antigos com Desconto: %d%n", imoveisAntigos);
        System.out.printf("Imóveis Padrão: %d%n", imoveis.size() - imoveisNovos - imoveisAntigos);
    }
}
