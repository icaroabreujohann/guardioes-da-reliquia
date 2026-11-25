package dominio.personagem;

import dominio.AcaoInvalidaException;

public abstract class Personagem {
    private final String nome;
    private final int vidaMaxima;
    private int vida;
    private final int ataque;
    private final int defesa;
    private boolean defendendo;

    protected Personagem(String nome, int vidaMaxima, int ataque, int defesa) {
        this.nome = validarNome(nome);
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
        exigirVivo();
        return alvo.receberDano(ataque);
    }

    public void defender() {
        exigirVivo();
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
        if (quantidade < 0) {
            throw new IllegalArgumentException("Quantidade de cura inválida: " + quantidade);
        }
        exigirVivo();
        int curado = Math.min(quantidade, vidaMaxima - vida);
        vida += curado;
        return curado;
    }

    public boolean estaVivo() {
        return vida > 0;
    }

    protected void exigirVivo() {
        if (!estaVivo()) {
            throw new AcaoInvalidaException(nome + " foi derrotado e não pode agir.");
        }
    }

    private static String validarNome(String nome) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("O nome do personagem não pode ser vazio.");
        }
        return nome;
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