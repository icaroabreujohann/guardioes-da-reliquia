package guardioes.dominio;

public class Inventario {

    private int pocoes;

    public Inventario(int pocoes) {
        if (pocoes < 0) {
            throw new IllegalArgumentException("Quantidade de poções inválida.");
        }
        this.pocoes = pocoes;
    }

    /** Gasta uma poção e cura 40. Recusa se não houver poção. */
    public int usarPocao(Personagem personagem) throws AcaoInvalidaException {
        // TODO
        throw new UnsupportedOperationException("TODO");
    }

    public void adicionarPocao() {
        pocoes++;
    }

    public int getPocoes() { return pocoes; }
}
