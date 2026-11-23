package guardioes.dominio;

/** Adversário controlado pelo computador. Cada um age de um jeito. */
public abstract class Inimigo extends Personagem {

    protected Inimigo(String nome, int vida, int ataque, int defesa) {
        super(nome, vida, ataque, defesa);
    }

    public static Inimigo criar(int encontro) {
        switch (encontro) {
            case 1: return new Goblin();
            case 2: return new Orc();
            case 3: return new Feiticeiro();
            default: throw new IllegalArgumentException("Encontro inválido: " + encontro);
        }
    }

    /** Decide e executa a ação do turno. Retorna o texto para o histórico. */
    public abstract String agir(Personagem jogador);
}
