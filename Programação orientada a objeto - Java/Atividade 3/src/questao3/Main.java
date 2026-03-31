package questao3;

public class Main {
    public static void main(String[] args) {

        Normal ingressoNormal = new Normal(50.0);
        ingressoNormal.imprimeValor();

        VIP ingressoVIP = new VIP(50.0, 25.0);
        ingressoVIP.imprimeValor();

        CamaroteInferior camaroteInferior = new CamaroteInferior(50.0, 25.0, "Setor A - Fila 5");
        camaroteInferior.imprimeValor();

        CamaroteSuperior camaroteSuperior = new CamaroteSuperior(50.0, 25.0, 50.0);
        camaroteSuperior.imprimeValor();
    }
}
