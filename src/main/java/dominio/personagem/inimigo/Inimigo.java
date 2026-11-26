package dominio.personagem.inimigo;

import dominio.personagem.Personagem;
import dominio.personagem.guardiao.Guardiao;

public abstract class Inimigo extends Personagem {

    protected Inimigo(String nome, int vidaMaxima, int ataque, int defesa) {
        super(nome, vidaMaxima, ataque, defesa);
    }

    /** Decide e executa a ação do turno. Retorna a mensagem do que aconteceu. */
    public final String agir(Guardiao jogador) {
        exigirVivo();
        return executarAcao(jogador);
    }

    protected abstract String executarAcao(Guardiao jogador);

    protected final String atacarNormalmente(Guardiao jogador) {
        int dano = atacar(jogador);
        return getNome() + " atacou " + jogador.getNome() + " e causou " + dano + " de dano.";
    }

    public abstract TipoInimigo getTipo();

    public abstract int getEstadoEspecial(); // cada inimigo decide o que precisa guardar no salvamento, assim a persistência não precisa saber a classe

    public abstract void restaurarEstadoEspecial(int valor);
}