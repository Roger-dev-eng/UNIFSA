package Classes;

public class Professor {
    private String nome;
    private int matricula;
    private double salario;

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getMatricula() {
        return matricula;
    }
    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public double getSalario() {
        return salario;
    }
    public void setSalario(double salario) {
        if (salario >= 0) {
            this.salario = salario;
        }
    }

    public double aumentarSalario(double valor) {
        if (valor >= 0) {
            salario = salario + valor;
        }
        return salario;
    }
}


