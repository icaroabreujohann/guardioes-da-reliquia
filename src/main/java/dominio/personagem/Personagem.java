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

    /**
     * Retorna dano que ira ser recebido pelo alvo
     */
    public int atacar(Personagem alvo) {
        return alvo.receberDano(ataque);
    }

    public void defender() {
        defendendo = true;
    }

    /**
     * Aplica o golpe com base na defesa e retorna o dano realmente perdido.
     */
    public int receberDano(int dano) {
        return receberDanoDireto(dano - defesa); //reutiliza função do dano direto, apenas desconta o atributo de defesa
    }


    /**
     * Ignora o atributo de defesa
     */
    public int receberDanoDireto(int dano) {
        int danoFinal = dano;
        if (defendendo) {
            danoFinal = danoFinal / 2;
            defendendo = false;
        }
        danoFinal = Math.max(1, danoFinal);
        danoFinal = Math.min(danoFinal, vida);

        vida -= danoFinal;
        return danoFinal;
    }

    /**
     * Cura até o limite da vida máxima e retorna quanto foi curado de fato.
     */
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