package dominio.personagem.inimigo;

import dominio.personagem.guardiao.Guardiao;

public class Orc extends Inimigo {
    private static final int VIDA_MAXIMA = 75;
    private static final int ATAQUE = 15;
    private static final int DEFESA = 4;
    private static final int INTERVALO_GOLPE_FORTE = 3;

    private int turnosAgidos;

    public Orc() {
        super(TipoInimigo.ORC.getDescricao(), VIDA_MAXIMA, ATAQUE, DEFESA);
    }

    @Override
    protected String executarAcao(Guardiao jogador) {
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
    public TipoInimigo getTipo() {
        return TipoInimigo.ORC;
    }

    @Override
    public int getEstadoEspecial() {
        return turnosAgidos; // sem isso, o Golpe Forte sairia no turno errado depois de carregar
    }

    @Override
    public void restaurarEstadoEspecial(int valor) {
        if (valor < 0)
            throw new IllegalArgumentException("Turnos do Orc inválidos: " + valor);

        turnosAgidos = valor;
    }
}