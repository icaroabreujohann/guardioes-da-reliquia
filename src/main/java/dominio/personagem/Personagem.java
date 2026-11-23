package dominio.personagem;

public abstract class Personagem {
    private final String nome;
    private final int vidaMaxima;
    private int vida;
    private final int ataque;
    private final int defesa;
    private boolean defendendo;

    protected Personagem(String nome, int vidaMaxima, int ataque, int defesa) {
        this.nome = nome;
        this.vidaMaxima = vidaMaxima;
        this.vida = vidaMaxima;
        this.ataque = ataque;
        this.defesa = defesa;
        this.defendendo = false;
    }

    /** Retorna dano que ira ser recebido pelo alvo */
    public int atacar(Personagem alvo) {
        return alvo.receberDano(ataque);
    }

    public void defender() {
        defendendo = true;
    }

    /** Aplica o golpe com base na defesa e retorna o dano realmente perdido. */
    public int receberDano(int dano) {
        int danoFinal = dano - defesa;

        if (defendendo) {
            danoFinal = danoFinal / 2;
            defendendo = false; // defesa gasta neste golpe
        }

        danoFinal = Math.max(1, danoFinal);
        danoFinal = Math.min(danoFinal, vida);    // não perde mais vida do que tem

        vida -= danoFinal;
        return danoFinal;
    }

    /** Cura até o limite da vida máxima e retorna quanto foi curado de fato. */
    public int curar(int quantidade) {
        int vidaAnterior = vida;
        vida = Math.min(vidaMaxima, vida + quantidade);
        return vida - vidaAnterior;
    }

    public boolean estaVivo() {
        return vida > 0;
    }


    public String getNome() {
        return nome;
    }

    public int getVidaMaxima() {
        return vidaMaxima;
    }

    public int getVida() {
        return vida;
    }

    public int getAtaque() {
        return ataque;
    }

    public int getDefesa() {
        return defesa;
    }

    public boolean isDefendendo() {
        return defendendo;
    }
}