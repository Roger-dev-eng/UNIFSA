import java.util.ArrayList;
import java.util.List;

public class SimuladorDeTrafego {
    private List<Veiculo> veiculosNaPista;
    private double velocidadeMaximaPista;
    private String condicoes;
    private static final int CAPACIDADE_MAXIMA_PISTA = 50;

    public SimuladorDeTrafego(double velocidadeMaximaPista) {
        this.veiculosNaPista = new ArrayList<>();
        this.velocidadeMaximaPista = velocidadeMaximaPista;
        this.condicoes = "Normal";
    }

    public SimuladorDeTrafego(double velocidadeMaximaPista, String condicoes) {
        this.veiculosNaPista = new ArrayList<>();
        this.velocidadeMaximaPista = velocidadeMaximaPista;
        this.condicoes = condicoes;
    }

    public void adicionarVeiculoPista(Veiculo veiculo) {
        // Verificação: pista cheia?
        if (veiculosNaPista.size() >= CAPACIDADE_MAXIMA_PISTA) {
            System.out.println("ERRO: Pista lotada! Capacidade máxima (" + CAPACIDADE_MAXIMA_PISTA + 
                    ") atingida. Não é possível adicionar " + veiculo.getModelo());
            return;
        }

        // Verificação: veículo já está na pista?
        if (veiculosNaPista.contains(veiculo)) {
            System.out.println("ERRO: Veículo com placa " + veiculo.getPlaca() + 
                    " já está na pista!");
            return;
        }

        veiculosNaPista.add(veiculo);
        System.out.println("✓ " + veiculo.getModelo() + " (Placa: " + veiculo.getPlaca() + 
                ") entrou na pista!");
    }

    public void removerVeiculoPista(Veiculo veiculo) {
        if (veiculosNaPista.remove(veiculo)) {
            System.out.println("✓ " + veiculo.getModelo() + " saiu da pista!");
        } else {
            System.out.println("ERRO: Veículo com placa " + veiculo.getPlaca() + 
                    " não está na pista!");
        }
    }

    public void ligarTodoVeiculos() {
        if (veiculosNaPista.isEmpty()) {
            System.out.println("ERRO: Nenhum veículo na pista para ligar!");
            return;
        }

        System.out.println("\n--- Ligando todos os veículos ---");
        for (Veiculo v : veiculosNaPista) {
            v.ligar();
        }
    }

    public void acelerarTodos() {
        if (veiculosNaPista.isEmpty()) {
            System.out.println("ERRO: Nenhum veículo na pista para acelerar!");
            return;
        }

        System.out.println("\n--- Acelerando todos os veículos ---");
        for (Veiculo v : veiculosNaPista) {
            // Verificação: combustível insuficiente?
            if (v.getCombustivel() <= 0) {
                System.out.println("⚠ " + v.getModelo() + " - SEM COMBUSTÍVEL! Impossível acelerar.");
                continue;
            }

            // Consumir combustível ao acelerar
            double consumo = v.calcularConsumo(1); // 1 km de consumo
            if (v.getCombustivel() >= consumo) {
                double novoCombustivel = v.getCombustivel() - consumo;
                // Simular aceleração
                if (v.getVelocidade() < velocidadeMaximaPista) {
                    v.acelerar();
                    System.out.println("✓ " + v.getModelo() + " acelerou para " + 
                            v.getVelocidade() + " km/h (Combustível: " + 
                            String.format("%.2f", novoCombustivel) + "L)");
                } else {
                    System.out.println("⚠ " + v.getModelo() + " atingiu velocidade máxima da pista!");
                }
            } else {
                System.out.println("⚠ " + v.getModelo() + " - combustível insuficiente para acelerar!");
            }
        }
    }

    public void frearTodos() {
        if (veiculosNaPista.isEmpty()) {
            System.out.println("ERRO: Nenhum veículo na pista para frear!");
            return;
        }

        System.out.println("\n--- Freando todos os veículos ---");
        for (Veiculo v : veiculosNaPista) {
            v.frear();
            System.out.println("✓ " + v.getModelo() + " freou para " + v.getVelocidade() + " km/h");
        }
    }

    public void exibirStatusTrafego() {
        System.out.println("\n========== STATUS DO TRÁFEGO ==========");
        System.out.println("Condições: " + condicoes);
        System.out.println("Velocidade Máxima da Pista: " + velocidadeMaximaPista + " km/h");
        System.out.println("Veículos na Pista: " + veiculosNaPista.size() + "/" + CAPACIDADE_MAXIMA_PISTA);

        if (veiculosNaPista.isEmpty()) {
            System.out.println("--- Nenhum veículo na pista ---");
        } else {
            System.out.println("----------------------------------------");
            for (Veiculo v : veiculosNaPista) {
                v.exibirStatus();
            }
        }
        System.out.println("========================================\n");
    }

    public void simularAceleracao(int iteracoes) {
        if (veiculosNaPista.isEmpty()) {
            System.out.println("ERRO: Nenhum veículo na pista para simular!");
            return;
        }

        if (iteracoes <= 0) {
            System.out.println("ERRO: Número de iterações deve ser positivo!");
            return;
        }

        System.out.println("\n--- Simulando aceleração por " + iteracoes + " iterações ---");
        for (int i = 0; i < iteracoes; i++) {
            System.out.println("\n[Iteração " + (i + 1) + "]");
            acelerarTodos();
        }
    }

    public void alterarCondicoes(String novasCondicoes) {
        if (novasCondicoes == null || novasCondicoes.isEmpty()) {
            System.out.println("ERRO: Condições inválidas!");
            return;
        }

        this.condicoes = novasCondicoes;
        System.out.println("✓ Condições da pista alteradas para: " + novasCondicoes);
    }

    public void listarVeiculos() {
        if (veiculosNaPista.isEmpty()) {
            System.out.println("\n--- Nenhum veículo na pista ---");
            return;
        }

        System.out.println("\n--- Veículos na Pista (" + veiculosNaPista.size() + ") ---");
        for (int i = 0; i < veiculosNaPista.size(); i++) {
            System.out.println((i + 1) + ". " + veiculosNaPista.get(i));
        }
    }

    public void simularInteracao(int indice1, int indice2) {
        if (indice1 < 0 || indice1 >= veiculosNaPista.size() || 
            indice2 < 0 || indice2 >= veiculosNaPista.size()) {
            System.out.println("ERRO: Índices de veículo inválidos!");
            return;
        }

        if (indice1 == indice2) {
            System.out.println("ERRO: Índices devem ser diferentes!");
            return;
        }

        Veiculo v1 = veiculosNaPista.get(indice1);
        Veiculo v2 = veiculosNaPista.get(indice2);

        System.out.println("\n--- Simulando interação ---");
        
        // Interação com tratamento de tipo Caminhão
        if (v2 instanceof Caminhao) {
            v1.interagir((Caminhao) v2);
        } else {
            v1.interagir(v2);
        }
    }

    public double getVelocidadeMaximaPista() {
        return velocidadeMaximaPista;
    }

    public void setVelocidadeMaximaPista(double velocidadeMaximaPista) {
        if (velocidadeMaximaPista <= 0) {
            System.out.println("ERRO: Velocidade máxima deve ser positiva!");
            return;
        }
        this.velocidadeMaximaPista = velocidadeMaximaPista;
        System.out.println("✓ Velocidade máxima da pista alterada para: " + velocidadeMaximaPista + " km/h");
    }

    public String getCondicoes() {
        return condicoes;
    }

    public int getTotalVeiculosNaPista() {
        return veiculosNaPista.size();
    }

    public int getCapacidadeMaximaPista() {
        return CAPACIDADE_MAXIMA_PISTA;
    }

    public List<Veiculo> getVeiculosNaPista() {
        return new ArrayList<>(veiculosNaPista);
    }
}
