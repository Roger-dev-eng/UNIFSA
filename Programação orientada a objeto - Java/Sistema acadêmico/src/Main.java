import Classes.AlunoGraduacao;
import Classes.AlunoPosGraduacao;

public class Main {

    public static void main(String[] args) {

        AlunoGraduacao a1 = new AlunoGraduacao();
        a1.setNome("Pedro");
        a1.setMatricula(123456);
        a1.setnotaFinal(4.5);
        a1.exibirDados();
        a1.verificarAprovacao();

        System.out.println();

        AlunoPosGraduacao a2 = new AlunoPosGraduacao();
        a2.setNome("João");
        a2.setMatricula(654321);
        a2.setnotaFinal(2.5);
        a2.exibirDados();
        a2.verificarAprovacao();
    }
}