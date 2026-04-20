public class Veiculo {
    private String marca;
    private String modelo;
    private double velocidade;
    private String placa;
    private double combustivel;
    private double tanqueCapacidade;
    private String tipoCombustivel;

    public Veiculo(String marca, String modelo, double velocidade) {
        this.marca = marca;
        this.modelo = modelo;
        this.velocidade = 0;
        this.placa = gerarPlaca();
        this.combustivel = 0;
        this.tanqueCapacidade = 60; // Capacidade padrão em litros
        this.tipoCombustivel = "Gasolina";
    }

    // Gera uma placa única para cada veículo
    private String gerarPlaca() {
        return "VEI-" + System.nanoTime();
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;       
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public double getVelocidade() {
        return velocidade;
    }

    public void setVelocidade(double velocidade) {
        if (velocidade >= 0) {
            this.velocidade = velocidade;
        }
    }

    public String getPlaca() {
        return placa;
    }

    public double getCombustivel() {
        return combustivel;
    }

    public String getTipoCombustivel() {
        return tipoCombustivel;
    }

    public void acelerar(int incremento) {
        if (combustivel > 0) {
            setVelocidade(getVelocidade() + incremento);
        } else {
            System.out.println("Sem combustível! Não é possível acelerar.");
        }
    }

    public void acelerar() {
        if (combustivel > 0) {
            setVelocidade(getVelocidade() + 10);
        } else {
            System.out.println("Sem combustível! Não é possível acelerar.");
        }
    }

    public void frear() {
        double novaVelocidade = getVelocidade() - 10;
        setVelocidade(novaVelocidade >= 0 ? novaVelocidade : 0);
    }

    public void frear(int incremento) {
        double novaVelocidade = getVelocidade() - incremento;
        setVelocidade(novaVelocidade >= 0 ? novaVelocidade : 0);
    }

    public void exibirStatus() {
        System.out.println("Veiculo: " + marca + " " + modelo +
                " - Placa: " + placa +
                " - Velocidade: " + velocidade + " km/h" +
                " - Combustível: " + String.format("%.2f", combustivel) + " L");
    }

    public void ligar() {
        System.out.println("Veículo " + marca + " " + modelo + " ligado!");
    }

    // Sobrecarga de abastecer
    public void abastecer(double litros) {
        abastecer(litros, this.tipoCombustivel);
    }

    public void abastecer(double litros, String tipoCombustivel) {
        if (litros <= 0) {
            System.out.println("Quantidade de combustível inválida!");
            return;
        }

        double novaQuantidade = combustivel + litros;
        if (novaQuantidade <= tanqueCapacidade) {
            combustivel = novaQuantidade;
            this.tipoCombustivel = tipoCombustivel;
            System.out.println("Abastecido com " + litros + "L de " + tipoCombustivel + 
                    ". Total: " + String.format("%.2f", combustivel) + "L");
        } else {
            System.out.println("Não é possível abastecer! Tanque transbordaria. Capacidade máxima: " + 
                    tanqueCapacidade + "L. Atual: " + String.format("%.2f", combustivel) + "L");
        }
    }

    // Método calcularConsumo - será sobrescrito nas subclasses
    public double calcularConsumo(double distancia) {
        // Consumo padrão: 10 km/L
        return distancia / 10.0;
    }

    // Método de interação entre veículos - sobrecarga
    public void interagir(Veiculo outro) {
        System.out.println(this.marca + " " + this.modelo + " detectou " + 
                outro.marca + " " + outro.modelo + " na pista!");
        
        if (this.velocidade > outro.velocidade) {
            System.out.println("Executando ultrapassagem!");
        } else if (this.velocidade == outro.velocidade) {
            System.out.println("Veículos em paralelo!");
        } else {
            System.out.println("Mantendo distância!");
        }
    }

    // Sobrecarga específica para interação com Caminhão
    public void interagir(Caminhao caminhao) {
        System.out.println(this.marca + " " + this.modelo + " detectou " + 
                caminhao.getMarca() + " " + caminhao.getModelo() + " (Caminhão)!");
        
        if (this.velocidade > caminhao.getVelocidade() + 5) {
            System.out.println("Ultrapassando caminhão com segurança!");
        } else if (this.velocidade <= caminhao.getVelocidade()) {
            System.out.println("Reduzindo velocidade para não colidir com o caminhão!");
            this.frear(5);
        } else {
            System.out.println("Aproximando-se do caminhão lentamente!");
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        
        Veiculo outro = (Veiculo) obj;
        return placa.equals(outro.placa);
    }

    @Override
    public int hashCode() {
        return placa.hashCode();
    }

    @Override
    public String toString() {
        return "Veiculo: " + marca + " " + modelo +
                " - Placa: " + placa +
                " - Velocidade: " + velocidade + " km/h" +
                " - Combustível: " + String.format("%.2f", combustivel) + "L";
    }
}
