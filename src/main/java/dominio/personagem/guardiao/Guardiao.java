package dominio.personagem.guardiao;


import dominio.exceptions.AcaoInvalidaException;
import dominio.personagem.Personagem;

public abstract class Guardiao extends Personagem {
    private final int recursoMaximo;
    private final int custoHabilidade;
    private int recurso;

    protected Guardiao(String nome, int vidaMaxima, int ataque, int defesa,
                       int recursoMaximo, int custoHabilidade) {
        super(nome, vidaMaxima, ataque, defesa);
        this.recursoMaximo = recursoMaximo;
        this.custoHabilidade = custoHabilidade;
        this.recurso = recursoMaximo;
    }

    /**
     * Gasta o recurso e aplica a habilidade da classe. Retorna a mensagem do que aconteceu.
     */
    public String usarHabilidade(Personagem alvo) throws AcaoInvalidaException {
        if (!podeUsarHabilidade()) {
            throw new AcaoInvalidaException(getNomeRecurso() + " insuficiente para " + getNomeHabilidade() + ".");
        }
        recurso -= custoHabilidade;
        int dano = aplicarHabilidade(alvo);
        return getNome() + " usou " + getNomeHabilidade() + " e causou " + dano + " de dano em " + alvo.getNome() + ".";
    }

    protected abstract int aplicarHabilidade(Personagem alvo);

    public boolean podeUsarHabilidade() {
        return recurso >= custoHabilidade;
    }

    /**
     * Chamado no início de cada encontro.
     */
    public void recuperarRecurso() {
        recurso = recursoMaximo;
    }

    public abstract String getNomeClasse();

    public abstract String getNomeRecurso();

    public abstract String getNomeHabilidade();

    public int getRecurso() {
        return recurso;
    }

    public int getRecursoMaximo() {
        return recursoMaximo;
    }
}