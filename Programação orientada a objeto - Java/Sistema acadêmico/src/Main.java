import Classes.AlunoGraduacao;
import Classes.AlunoPosGraduacao;
import Classes.Disciplina;
import Classes.ProfessorEfetivo;
import Classes.ProfessorTemporario;

public class Main {

    public static void main(String[] args) {

        AlunoGraduacao a1 = new AlunoGraduacao();
        a1.setNome("Pedro");
        a1.setMatricula(123456);
        a1.setNotaFinal(4.5);
        a1.verificarAprovacao();
        a1.ajustarNota(10);
        a1.exibirDados();

        System.out.println();

        AlunoPosGraduacao a2 = new AlunoPosGraduacao();
        a2.setNome("João");
        a2.setMatricula(654321);
        a2.setNotaFinal(2.5);
        a2.exibirDados();
        a2.verificarAprovacao();

        System.out.println();

        ProfessorEfetivo professorEfetivo = new ProfessorEfetivo("Maria", 1001, 7000);
        ProfessorTemporario professorTemporario = new ProfessorTemporario("Carlos", 2001, 4500, 12);

        professorEfetivo.aumentarSalario(500);
        professorTemporario.aumentarSalario(300);

        Disciplina disciplina = new Disciplina("Programação Orientada a Objetos", professorEfetivo);
        disciplina.adicionarAluno(a1);
        disciplina.adicionarAluno(a2);

        System.out.println("Disciplina: " + disciplina.getNome());
        System.out.println("Professor: " + disciplina.getProfessor().getNome());
        System.out.println("Quantidade de alunos: " + disciplina.getAlunos().size());

        System.out.println("\nProfessor Temporário: " + professorTemporario.getNome());
        System.out.println("Salário atual: " + professorTemporario.getSalario());
    }
}
