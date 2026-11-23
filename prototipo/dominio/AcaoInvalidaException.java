package guardioes.dominio;

/** Ação que não pode ser feita agora (sem recurso, sem poção, partida encerrada...). */
public class AcaoInvalidaException extends Exception {

    private static final long serialVersionUID = 1L;

    public AcaoInvalidaException(String mensagem) {
        super(mensagem);
    }
}
