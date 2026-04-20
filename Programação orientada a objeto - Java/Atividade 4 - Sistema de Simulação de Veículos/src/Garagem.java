import java.util.ArrayList;
import java.util.List;

public class Garagem {
    private List<Veiculo> veiculos;

    public Garagem() {
        veiculos = new ArrayList<>();
    }

    public void adicionarVeiculo(Veiculo veiculo) {
        // Verificação: veículo já existe com essa placa?
        for (Veiculo v : veiculos) {
            if (v.equals(veiculo)) {
                System.out.println("ERRO: Veículo com placa " + veiculo.getPlaca() + 
                        " já está na garagem!");
                return;
            }
        }

        veiculos.add(veiculo);
        System.out.println("✓ " + veiculo.getModelo() + " adicionado à garagem.");
    }

    public void listarVeiculo() {
        if (veiculos.isEmpty()) {
            System.out.println("Garagem vazia!");
            return;
        }

        System.out.println("\n--- Veículos na Garagem (" + veiculos.size() + ") ---");
        for (int i = 0; i < veiculos.size(); i++) {
            System.out.println((i + 1) + ". " + veiculos.get(i));
        }
    }

    public void estacionar(Carro c) {
        if (c == null) {
            System.out.println("ERRO: Carro nulo!");
            return;
        }
        System.out.println("✓ " + c.getModelo() + " (Placa: " + c.getPlaca() + ") estacionado");
    }

    public void estacionar(Moto m) {
        if (m == null) {
            System.out.println("ERRO: Moto nula!");
            return;
        }
        System.out.println("✓ " + m.getModelo() + " (Placa: " + m.getPlaca() + ") estacionada");
    }

    public void estacionar(Caminhao c) {
        if (c == null) {
            System.out.println("ERRO: Caminhão nulo!");
            return;
        }
        System.out.println("✓ " + c.getModelo() + " (Placa: " + c.getPlaca() + ") estacionado");
    }

    public Veiculo buscarPorPlaca(String placa) {
        for (Veiculo v : veiculos) {
            if (v.getPlaca().equals(placa)) {
                return v;
            }
        }
        return null;
    }

    public int getTotalVeiculos() {
        return veiculos.size();
    }
}
