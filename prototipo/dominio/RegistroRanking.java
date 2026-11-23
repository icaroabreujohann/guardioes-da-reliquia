package guardioes.dominio;

public class RegistroRanking implements Comparable<RegistroRanking> {

    private final String nome;
    private final String classe;
    private final int pontuacao;
    private final String resultado;
    private final int turnos;

    public RegistroRanking(String nome, String classe, int pontuacao, String resultado, int turnos) {
        this.nome = nome;
        this.classe = classe;
        this.pontuacao = pontuacao;
        this.resultado = resultado;
        this.turnos = turnos;
    }

    /** Maior pontuação primeiro; empate: menos turnos. */
    @Override
    public int compareTo(RegistroRanking outro) {
        // TODO
        throw new UnsupportedOperationException("TODO");
    }

    public String getNome() { return nome; }
    public String getClasse() { return classe; }
    public int getPontuacao() { return pontuacao; }
    public String getResultado() { return resultado; }
    public int getTurnos() { return turnos; }
}
