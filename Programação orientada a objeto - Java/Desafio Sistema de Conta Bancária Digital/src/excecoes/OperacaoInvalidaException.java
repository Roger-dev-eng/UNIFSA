package excecoes;

public class OperacaoInvalidaException extends Exception {
    private final String operacao;

    public OperacaoInvalidaException(String operacao) {
        super("Operacao invalida: " + operacao + ".");
        this.operacao = operacao;
    }

    public OperacaoInvalidaException(String message, String operacao) {
        super(message);
        this.operacao = operacao;
    }

    public String getOperacao() {
        return operacao;
    }
}
