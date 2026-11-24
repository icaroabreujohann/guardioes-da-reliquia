package dominio.personagem.inimigo;
import dominio.personagem.Personagem;
import dominio.personagem.guardiao.Guardiao;

public abstract class Inimigo extends Personagem {

    protected Inimigo(String nome, int vidaMaxima, int ataque, int defesa) {
        super(nome, vidaMaxima, ataque, defesa);
    }

    /** Decide e executa a ação do turno. Retorna a mensagem do que aconteceu. */
    public abstract String agir(Guardiao jogador);

    protected String atacarNormalmente(Guardiao jogador) {
        int dano = atacar(jogador);
        return getNome() + " atacou " + jogador.getNome() + " e causou " + dano + " de dano.";
    }

    public abstract String getNomeInimigo();
}