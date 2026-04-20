public class Caminhao extends Veiculo {
    private double capacidadeCarga;
    private double cargaAtual;

    public Caminhao(String marca, String modelo, double capacidadeCarga) {
        super(marca, modelo, 0);
        this.capacidadeCarga = capacidadeCarga;
        this.cargaAtual = 0;
    }

    public Caminhao(String marca, String modelo, double velocidade, double capacidadeCarga) {
        super(marca, modelo, velocidade);
        this.capacidadeCarga = capacidadeCarga;
        this.cargaAtual = 0;
    }

    public double getCapacidadeCarga() {
        return capacidadeCarga;
    }

    public void setCapacidadeCarga(double capacidadeCarga) {
        this.capacidadeCarga = capacidadeCarga;
    }

    public double getCargaAtual() {
        return cargaAtual;
    }

    public void setCargaAtual(double cargaAtual) {
        if (cargaAtual >= 0 && cargaAtual <= capacidadeCarga) {
            this.cargaAtual = cargaAtual;
        } else {
            System.out.println("Carga inválida! Capacidade máxima: " + capacidadeCarga + " kg");
        }
    }

    @Override
    public void acelerar() {
        if (getCombustivel() > 0) {
            // Caminhão carregado acelera mais lentamente
            double incremento = (cargaAtual > 0) ? 5 : 8;
            setVelocidade(getVelocidade() + incremento);
        } else {
            System.out.println("Sem combustível! Não é possível acelerar.");
        }
    }

    @Override
    public double calcularConsumo(double distancia) {
        // Caminhões têm alto consumo: 4 km/L (10% a menos se carregado)
        double consumoBase = distancia / 4.0;
        if (cargaAtual > 0) {
            consumoBase *= 1.1; // 10% a mais de consumo se carregado
        }
        return consumoBase;
    }

    @Override
    public void exibirStatus() {
        System.out.println("Caminhão: " + getMarca() + " " + getModelo() +
                " - Placa: " + getPlaca() +
                " - Carga: " + cargaAtual + "/" + capacidadeCarga + " kg" +
                " - Velocidade: " + getVelocidade() + " km/h" +
                " - Combustível: " + String.format("%.2f", getCombustivel()) + "L");
    }

    @Override
    public String toString() {
        return "Caminhão: " + getMarca() + " " + getModelo() +
               " - Placa: " + getPlaca() +
               " - Capacidade: " + capacidadeCarga + " kg" +
               " - Velocidade: " + getVelocidade() + " km/h";
    }

    public void ligar(String modo) {
        System.out.println("Caminhão " + getMarca() + " " + getModelo() +
                " ligado em modo " + modo + "!");
    }

    public void carregarCarga(double quantidade) {
        double novaCarga = cargaAtual + quantidade;
        if (novaCarga <= capacidadeCarga) {
            cargaAtual = novaCarga;
            System.out.println("Carga de " + quantidade + " kg adicionada. Total: " + cargaAtual + " kg");
        } else {
            System.out.println("Não é possível adicionar " + quantidade + " kg. Capacidade insuficiente!");
        }
    }

    public void descarregarCarga(double quantidade) {
        if (quantidade <= cargaAtual) {
            cargaAtual -= quantidade;
            System.out.println("Carga de " + quantidade + " kg removida. Total: " + cargaAtual + " kg");
        } else {
            System.out.println("Não é possível remover " + quantidade + " kg. Carga insuficiente!");
        }
    }
}
