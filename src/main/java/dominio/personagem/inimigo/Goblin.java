package dominio.personagem.inimigo;

import dominio.personagem.guardiao.Guardiao;

public class Goblin extends Inimigo {
    private static final int VIDA_MAXIMA = 40;
    private static final int ATAQUE = 8;
    private static final int DEFESA = 1;

    public Goblin() {
        super(TipoInimigo.GOBLIN.getDescricao(), VIDA_MAXIMA, ATAQUE, DEFESA);
    }

    @Override
    protected String executarAcao(Guardiao jogador) {
        return atacarNormalmente(jogador);
    }

    @Override
    public TipoInimigo getTipo() {
        return TipoInimigo.GOBLIN;
    }
}