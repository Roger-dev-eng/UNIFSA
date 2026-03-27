package Classes;

public class AlunoGraduacao extends Aluno {
    public AlunoGraduacao() {
    }

    @Override
    public void verificarAprovacao() {
        if (getNotaFinal() >= 7) {
            System.out.println("Aluno aprovado.");
        }
        else {
            System.out.println("Aluno reprovado.");
        }
    }
}
