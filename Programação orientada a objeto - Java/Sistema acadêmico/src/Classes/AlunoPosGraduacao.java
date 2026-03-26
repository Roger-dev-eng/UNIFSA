package Classes;

public class AlunoPosGraduacao extends Aluno {
    public AlunoPosGraduacao() {
    }

    public void verificarAprovacao() {
        if (getnotaFinal() >= 6) {
            System.out.println("Aluno aprovado.");
        }
        else {
            System.out.println("Aluno reprovado.");
        }
    }
}
