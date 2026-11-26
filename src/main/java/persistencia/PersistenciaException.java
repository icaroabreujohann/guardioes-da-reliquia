package persistencia;

public class PersistenciaException extends Exception { // verificada, para o compilador obrigar a tela a tratar as falhas de arquivo
    public PersistenciaException(String mensagem) {
        super(mensagem);
    }

    public PersistenciaException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}
