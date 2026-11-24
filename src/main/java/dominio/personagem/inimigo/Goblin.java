package dominio.personagem.inimigo;

import dominio.personagem.guardiao.Guardiao;

public class Goblin extends Inimigo {
    private static final String NOME = "Goblin";
    private static final int VIDA_MAXIMA = 40;
    private static final int ATAQUE = 8;
    private static final int DEFESA = 1;

    public Goblin() {
        super(NOME, VIDA_MAXIMA, ATAQUE, DEFESA);
    }

    @Override
    public String agir(Guardiao jogador) {
        return atacarNormalmente(jogador);
    }

    @Override
    public String getNomeInimigo() {
        return NOME;
    }
}