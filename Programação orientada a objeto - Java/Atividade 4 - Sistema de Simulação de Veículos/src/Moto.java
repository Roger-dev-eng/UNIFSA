public class Moto extends Veiculo {
    private double cilindradas;

    public Moto(String marca, String modelo, double velocidade, double cilindradas) {
        super(marca, modelo, velocidade);
        this.cilindradas = cilindradas;
    }

    public Moto(String marca, String modelo, double cilindradas) {
        super(marca, modelo, 0);
        this.cilindradas = cilindradas;
    }

    public Moto(String marca, String modelo) {
        super(marca, modelo, 0);
        this.cilindradas = 0;
    }

    public double getCilindradas() {
        return cilindradas;
    }

    public void setCilindradas(double cilindradas) {
        if (cilindradas > 0) {
            this.cilindradas = cilindradas;
        }
    }

    @Override
    public void acelerar() {
        if (getCombustivel() > 0) {
            setVelocidade(getVelocidade() + 15);
        } else {
            System.out.println("Sem combustível! Não é possível acelerar.");
        }
    }

    @Override
    public double calcularConsumo(double distancia) {
        // Motos têm consumo eficiente: 20 km/L
        return distancia / 20.0;
    }

    @Override
    public void exibirStatus() {
        System.out.println("Moto: " + getMarca() + " " + getModelo() +
                " - Placa: " + getPlaca() +
                " - Cilindradas: " + cilindradas + "cc" +
                " - Velocidade: " + getVelocidade() + " km/h" +
                " - Combustível: " + String.format("%.2f", getCombustivel()) + "L");
    }

    @Override
    public String toString() {
        return "Moto: " + getMarca() + " " + getModelo() +
               " - Placa: " + getPlaca() +
               " - Cilindradas: " + cilindradas + "cc" +
               " - Velocidade: " + getVelocidade() + " km/h";
    }

    public void ligar(String modo) {
        System.out.println("Moto " + getMarca() + " " + getModelo() +
                " ligada em modo " + modo + "!");
    }
}
