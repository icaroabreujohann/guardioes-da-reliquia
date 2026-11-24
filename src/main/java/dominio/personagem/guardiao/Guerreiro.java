package dominio.personagem.guardiao;

import dominio.enums.TipoGuardiao;
import dominio.personagem.Personagem;

public class Guerreiro extends Guardiao {
    private static final int VIDA_MAXIMA = 110;
    private static final int ATAQUE = 12;
    private static final int DEFESA = 5;
    private static final int RECURSO_MAXIMO = 30;
    private static final int CUSTO_HABILIDADE = 10;

    public Guerreiro(String nome) {
        super(nome, VIDA_MAXIMA, ATAQUE, DEFESA, RECURSO_MAXIMO, CUSTO_HABILIDADE);
    }

    @Override
    protected int aplicarHabilidade(Personagem alvo) {
        return alvo.receberDano(ATAQUE * 2); //Golpe Poderoso: 2 × ataque − defesa do alvo
    }

    @Override
    public TipoGuardiao getTipo() {
        return TipoGuardiao.GUERREIRO;
    }

    @Override
    public String getNomeRecurso() {
        return "Energia";
    }

    @Override
    public String getNomeHabilidade() {
        return "Golpe Poderoso";
    }
}