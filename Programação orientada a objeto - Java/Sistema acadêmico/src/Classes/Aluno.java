package Classes;

public abstract class Aluno {

    private String nome;
    private int matricula;
    private double notaFinal;

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

    public double getNotaFinal() {
        return notaFinal;
    }

    public void setNotaFinal(double notaFinal) {
        if (notaFinal < 0 || notaFinal > 10) {
            throw new IllegalArgumentException("A nota final deve estar entre 0 e 10.");
        }
        this.notaFinal = notaFinal;
    }

    public void ajustarNota(double novaNota) {
        setNotaFinal(novaNota);
    }

    public abstract void verificarAprovacao();

    public void exibirDados() {
        System.out.println("Nome do aluno: " + nome +
                           "\nMatrícula: " + matricula +
                           "\nNota Final: " + notaFinal);
    }
}
