package guardioes.dominio;

/** Sempre ataca. */
public class Goblin extends Inimigo {

    public Goblin() {
        super("Goblin", 40, 8, 1);
    }

    @Override
    public String agir(Personagem jogador) {
        // TODO
        throw new UnsupportedOperationException("TODO");
    }
}
