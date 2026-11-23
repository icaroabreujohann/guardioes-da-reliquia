package guardioes.dominio;

/** Personagem do jogador, com o recurso gasto pela habilidade. */
public abstract class Guardiao extends Personagem {

    private final int recursoMaximo;
    private int recurso;
    private final int custoHabilidade;

    protected Guardiao(String nome, int vida, int ataque, int defesa, int recursoMaximo, int custoHabilidade) {
        super(nome, vida, ataque, defesa);
        this.recursoMaximo = recursoMaximo;
        this.recurso = recursoMaximo;
        this.custoHabilidade = custoHabilidade;
    }

    public static Guardiao criar(String classe, String nome) {
        switch (classe) {
            case "Guerreiro": return new Guerreiro(nome);
            case "Arqueiro":  return new Arqueiro(nome);
            case "Mago":      return new Mago(nome);
            default: throw new IllegalArgumentException("Classe desconhecida: " + classe);
        }
    }

    /** Confere e gasta o recurso; o efeito fica com cada classe. */
    public String usarHabilidade(Personagem alvo) throws AcaoInvalidaException {
        // TODO recusar se recurso < custoHabilidade; gastar; return aplicarHabilidade(alvo);
        throw new UnsupportedOperationException("TODO");
    }

    protected abstract String aplicarHabilidade(Personagem alvo);

    public abstract String getClasse();

    public void recuperarRecurso() {
        recurso = recursoMaximo;
    }

    public void setRecurso(int recurso) {
        if (recurso < 0 || recurso > recursoMaximo) {
            throw new IllegalArgumentException("Recurso inválido.");
        }
        this.recurso = recurso;
    }

    public int getRecurso() { return recurso; }
    public int getRecursoMaximo() { return recursoMaximo; }
}
