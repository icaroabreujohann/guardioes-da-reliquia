package dominio.item;

import dominio.AcaoInvalidaException;
import dominio.personagem.Personagem;

public class Inventario {
    private static final int POCOES_INICIAIS = 2;
    private static final int CURA_POCAO = 40;

    private int pocoes;

    public Inventario() {
        this(POCOES_INICIAIS);
    }

    public Inventario(int pocoes) { // recebe a quantidade para poder restaurar um jogo salvo
        if (pocoes < 0)
            throw new IllegalArgumentException("Quantidade de poções inválida: " + pocoes);

        this.pocoes = pocoes;
    }

    public int usarPocao(Personagem personagem) throws AcaoInvalidaException {
        if (!temPocao())
            throw new AcaoInvalidaException("Não há poções no inventário.");

        if (!personagem.estaVivo())
            throw new AcaoInvalidaException(personagem.getNome() + " foi derrotado e não pode usar poção.");

        pocoes--; // só gasta a poção depois que as duas verificações passaram
        return personagem.curar(CURA_POCAO); // devolve o quanto curou de fato, que pode ser menos de 40 perto da vida máxima
    }

    public void adicionarPocao() {
        pocoes++;
    }

    public boolean temPocao() {
        return pocoes > 0;
    }

    public int getPocoes() {
        return pocoes;
    }

    public static int getPocoesIniciais() {
        return POCOES_INICIAIS;
    }
}
