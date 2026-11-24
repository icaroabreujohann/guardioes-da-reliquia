package dominio.personagem.inimigo;

import dominio.personagem.guardiao.Guardiao;

public class Orc extends Inimigo {
    private static final String NOME = "Orc";
    private static final int VIDA_MAXIMA = 75;
    private static final int ATAQUE = 15;
    private static final int DEFESA = 4;
    private static final int INTERVALO_GOLPE_FORTE = 3;

    private int turnosAgidos;

    public Orc() {
        super(NOME, VIDA_MAXIMA, ATAQUE, DEFESA);
    }

    @Override
    public String agir(Guardiao jogador) {
        turnosAgidos++;
        if (turnosAgidos % INTERVALO_GOLPE_FORTE == 0) {
            return usarGolpeForte(jogador);
        }
        return atacarNormalmente(jogador);
    }
    
    private String usarGolpeForte(Guardiao jogador) {
        int dano = jogador.receberDano(getAtaque() * 3 / 2); // Golpe forte: 1,5 × ataque − defesa do alvo
        return getNome() + " usou Golpe Forte em " + jogador.getNome() + " e causou " + dano + " de dano.";
    }

    @Override
    public String getNomeInimigo() {
        return NOME;
    }
}