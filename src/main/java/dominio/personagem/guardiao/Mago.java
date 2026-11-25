package dominio.personagem.guardiao;

import dominio.personagem.Personagem;

public class Mago extends Guardiao {
    private static final int VIDA_MAXIMA = 90;
    private static final int ATAQUE = 8;
    private static final int DEFESA = 2;
    private static final int RECURSO_MAXIMO = 45;
    private static final int CUSTO_HABILIDADE = 15;
    private static final int DANO_HABILIDADE = 35;

    public Mago(String nome) {
        super(nome, VIDA_MAXIMA, ATAQUE, DEFESA, RECURSO_MAXIMO, CUSTO_HABILIDADE);
    }

    @Override
    protected int aplicarHabilidade(Personagem alvo) {
        return alvo.receberDanoDireto(DANO_HABILIDADE); //Bola de Fogo: Dano de 35, fixo.
    }

    @Override
    public TipoGuardiao getTipo() {
        return TipoGuardiao.MAGO;
    }

    @Override
    public String getNomeRecurso() {
        return "Mana";
    }

    @Override
    public String getNomeHabilidade() {
        return "Bola de Fogo";
    }
}