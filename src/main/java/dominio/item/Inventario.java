package dominio.item;

import dominio.exceptions.AcaoInvalidaException;
import dominio.personagem.Personagem;

/**
 * Onde ficam as poções do jogador.
 */
public class Inventario {
    private static final int POCOES_INICIAIS = 2;
    private static final int CURA_POCAO = 40;

    private int pocoes;

    /**
     * Partida nova: começa com 2 poções.
     */
    public Inventario() {
        this(POCOES_INICIAIS);
    }

    /**
     * Começa com a quantidade que quiser (vai servir pra carregar um jogo salvo).
     * Não aceita número negativo.
     */
    public Inventario(int pocoes) {
        if (pocoes < 0) {
            throw new IllegalArgumentException("Quantidade de poções inválida: " + pocoes);
        }
        this.pocoes = pocoes;
    }

    /**
     * Bebe uma poção e cura 40 (sem passar da vida máxima). Retorna quanto curou de verdade.
     * Se acabaram as poções ou o personagem já morreu, dá erro antes de gastar qualquer coisa.
     */
    public int usarPocao(Personagem personagem) throws AcaoInvalidaException {
        if (!temPocao()) {
            throw new AcaoInvalidaException("Não há poções no inventário.");
        }
        if (!personagem.estaVivo()) {
            throw new AcaoInvalidaException(personagem.getNome() + " foi derrotado e não pode usar poção.");
        }
        pocoes--;
        return personagem.curar(CURA_POCAO);
    }

    /**
     * Ganha mais uma poção (quando vence o encontro 1 ou 2).
     */
    public void adicionarPocao() {
        pocoes++;
    }

    /**
     * Diz se ainda tem poção.
     */
    public boolean temPocao() {
        return pocoes > 0;
    }

    public int getPocoes() {
        return pocoes;
    }
}
