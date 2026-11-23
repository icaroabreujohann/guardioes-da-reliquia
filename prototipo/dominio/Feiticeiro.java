package guardioes.dominio;

/** Ataca; uma única vez, com vida abaixo de 40%, se cura em 20 em vez de atacar. */
public class Feiticeiro extends Inimigo {

    private boolean jaSeCurou;

    public Feiticeiro() {
        super("Feiticeiro", 110, 18, 3);
    }

    @Override
    public String agir(Personagem jogador) {
        // TODO
        throw new UnsupportedOperationException("TODO");
    }

    public boolean isJaSeCurou() { return jaSeCurou; }
    public void setJaSeCurou(boolean jaSeCurou) { this.jaSeCurou = jaSeCurou; }
}
