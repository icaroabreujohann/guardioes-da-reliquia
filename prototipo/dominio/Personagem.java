package guardioes.dominio;

/** Base de tudo que luta: guardiões e inimigos. */
public abstract class Personagem {

    private final String nome;
    private final int vidaMaxima;
    private int vida;
    private final int ataque;
    private final int defesa;
    private boolean defendendo;

    protected Personagem(String nome, int vidaMaxima, int ataque, int defesa) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("O nome não pode ficar vazio.");
        }
        this.nome = nome.trim();
        this.vidaMaxima = vidaMaxima;
        this.vida = vidaMaxima;
        this.ataque = ataque;
        this.defesa = defesa;
    }

    /** dano = max(1, ataque - defesa do alvo). Retorna o dano causado. */
    public int atacar(Personagem alvo) {
        // TODO
        throw new UnsupportedOperationException("TODO");
    }

    /** O próximo dano recebido cai pela metade. */
    public void defender() {
        // TODO
        throw new UnsupportedOperationException("TODO");
    }

    /** Aplica o dano (metade se estiver defendendo). Vida nunca abaixo de 0. */
    public int receberDano(int dano) {
        // TODO
        throw new UnsupportedOperationException("TODO");
    }

    /** Cura sem passar da vida máxima. Retorna quanto curou. */
    public int curar(int quantidade) {
        // TODO
        throw new UnsupportedOperationException("TODO");
    }

    public boolean estaVivo() {
        return vida > 0;
    }

    /** Usado só ao carregar uma partida salva. */
    public void restaurar(int vida, boolean defendendo) {
        if (vida < 0 || vida > vidaMaxima) {
            throw new IllegalArgumentException("Vida inválida.");
        }
        this.vida = vida;
        this.defendendo = defendendo;
    }

    public String getNome() { return nome; }
    public int getVidaMaxima() { return vidaMaxima; }
    public int getVida() { return vida; }
    public int getAtaque() { return ataque; }
    public int getDefesa() { return defesa; }
    public boolean isDefendendo() { return defendendo; }
}
