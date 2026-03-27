package Classes;

public class AlunoPosGraduacao extends Aluno {
    public AlunoPosGraduacao() {
    }

    @Override
    public void verificarAprovacao() {
        if (getNotaFinal() >= 6) {
            System.out.println("Aluno aprovado.");
        }
        else {
            System.out.println("Aluno reprovado.");
        }
    }
}
