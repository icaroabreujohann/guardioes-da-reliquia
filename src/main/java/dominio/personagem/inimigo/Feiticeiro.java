package dominio.personagem.inimigo;

import dominio.enums.TipoInimigo;
import dominio.personagem.guardiao.Guardiao;

public class Feiticeiro extends Inimigo {
    private static final int VIDA_MAXIMA = 110;
    private static final int ATAQUE = 18;
    private static final int DEFESA = 3;
    private static final int PERCENTUAL_VIDA_PARA_CURA = 40;
    private static final int CURA = 20;

    private boolean jaSeCurou;

    public Feiticeiro() {
        super(TipoInimigo.FEITICEIRO.getDescricao(), VIDA_MAXIMA, ATAQUE, DEFESA);
    }

    @Override
    protected String executarAcao(Guardiao jogador) {
        if (!jaSeCurou && estaComVidaBaixa()) {
            return seCurar();
        }
        return atacarNormalmente(jogador);
    }

    private boolean estaComVidaBaixa() {
        return getVida() * 100 < getVidaMaxima() * PERCENTUAL_VIDA_PARA_CURA;
    }

    private String seCurar() {
        jaSeCurou = true;
        int curado = curar(CURA);
        return getNome() + " se curou em " + curado + " de vida.";
    }

    @Override
    public TipoInimigo getTipo() {
        return TipoInimigo.FEITICEIRO;
    }
}