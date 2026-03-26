package Classes;

public class Aluno {

    private String nome;
    private int matricula;
    private Double notaFinal;

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

    public Double getnotaFinal() {
        return notaFinal;
    }

    public void setnotaFinal(Double notaFinal) {
        if (notaFinal >= 0 && notaFinal <= 10) {
            this.notaFinal = notaFinal;
        }
    }

    public void ajustarNota(double novaNota) {
        setnotaFinal(novaNota);
    }

    public void exibirDados() {
        System.out.println("Nome do aluno: " + nome +
                           "\nMatrícula: " + matricula +
                           "\nNota Final: " + notaFinal);
    }
}