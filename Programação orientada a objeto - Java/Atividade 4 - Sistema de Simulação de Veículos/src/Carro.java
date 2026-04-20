public class Carro extends Veiculo {
    private int numeroPortas;

    public Carro(String marca, String modelo, int numeroPortas) {
        super(marca, modelo, 0);
        this.numeroPortas = numeroPortas;
    }

    public Carro(String marca, String modelo, double velocidade, int numeroPortas) {
        super(marca, modelo, velocidade);
        this.numeroPortas = numeroPortas;
    }

    public int getNumeroPortas() {
        return numeroPortas;
    }

    public void setNumeroPortas(int numeroPortas) {
        if (numeroPortas > 0) {
            this.numeroPortas = numeroPortas;
        }
    }

    @Override
    public double calcularConsumo(double distancia) {
        // Carros têm consumo moderado: 12 km/L
        return distancia / 12.0;
    }

    @Override
    public String toString() {
        return "Carro: " + getMarca() + " " + getModelo() +
               " - Placa: " + getPlaca() +
               " - Velocidade: " + getVelocidade() + " km/h" +
               " - Portas: " + numeroPortas;
    }

    @Override
    public void exibirStatus() {
        System.out.println("Carro: " + getMarca() + " " + getModelo() +
                " - Placa: " + getPlaca() +
                " - Velocidade: " + getVelocidade() + " km/h" +
                " - Portas: " + numeroPortas +
                " - Combustível: " + String.format("%.2f", getCombustivel()) + "L");
    }

    public void ligar(String modo) {
        System.out.println("Carro " + getMarca() + " " + getModelo() +
                " ligado em modo " + modo + "!");
    }
}
