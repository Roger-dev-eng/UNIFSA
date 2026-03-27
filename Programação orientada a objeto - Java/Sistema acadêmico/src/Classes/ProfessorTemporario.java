package Classes;

public class ProfessorTemporario extends Professor {
    private int duracaoContrato;

    public ProfessorTemporario(String nome, int matricula, double salario, int duracaoContrato) {
        setNome(nome);
        setMatricula(matricula);
        setSalario(salario);
        this.duracaoContrato = duracaoContrato;
    }

    public int getDuracaoContrato() {
        return duracaoContrato;
    }

    public void setDuracaoContrato(int duracaoContrato) {
        if (duracaoContrato < 0) {
            throw new IllegalArgumentException("A duração do contrato não pode ser negativa.");
        }
        this.duracaoContrato = duracaoContrato;
    }

    @Override
    public double aumentarSalario(double valor) {
        if (duracaoContrato <= 0) {
            System.out.println("Contrato encerrado.");
            return getSalario();
        }
        else {
            return super.aumentarSalario(valor);
        }
    }
}
