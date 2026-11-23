package guardioes.dominio;

/** Ataca; a cada 3º turno dele, Golpe Forte (ataque x 3 / 2). */
public class Orc extends Inimigo {

    private int turnos;

    public Orc() {
        super("Orc", 75, 15, 4);
    }

    @Override
    public String agir(Personagem jogador) {
        // TODO
        throw new UnsupportedOperationException("TODO");
    }

    public int getTurnos() { return turnos; }
    public void setTurnos(int turnos) { this.turnos = turnos; }
}
