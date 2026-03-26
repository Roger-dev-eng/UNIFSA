package Classes;

public class AlunoGraduacao extends Aluno {
    public AlunoGraduacao() {
    }

    public void verificarAprovacao() {
        if (getnotaFinal() >= 7) {
            System.out.println("Aluno aprovado.");
        }
        else {
            System.out.println("Aluno reprovado.");
        }
    }
}
