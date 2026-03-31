package questao3;

public class CamaroteInferior extends VIP {
    private String localizacao;

    public CamaroteInferior(double valor, double valorAdicional, String localizacao) {
        super(valor, valorAdicional);
        this.localizacao = localizacao;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public void imprimeLocalizacao() {
        System.out.println("\nLocalização do Camarote Inferior: " + localizacao);
    }

    @Override
    public void imprimeValor() {
        System.out.printf("\nValor Total Camarote Inferior: R$ "+getValorTotal()+" (base: R$ 50,00 + adicional: R$ 25,00)");
        imprimeLocalizacao();
    }
}
