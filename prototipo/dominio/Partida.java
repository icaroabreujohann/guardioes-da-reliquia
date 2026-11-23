package guardioes.dominio;

import java.util.ArrayList;
import java.util.List;

/**
 * Estado da partida e fluxo dos turnos. Cada ação do jogador é uma rodada:
 * a ação dele e, se o inimigo continuar vivo, a resposta do inimigo.
 */
public class Partida {

    private final Guardiao jogador;
    private Inimigo inimigo;
    private final Inventario inventario;
    private int encontro;
    private int turno;
    private final List<String> historico = new ArrayList<>();

    /** Nova partida. */
    public Partida(Guardiao jogador) {
        this(jogador, Inimigo.criar(1), new Inventario(2), 1, 1);
    }

    /** Partida carregada do arquivo. */
    public Partida(Guardiao jogador, Inimigo inimigo, Inventario inventario, int encontro, int turno) {
        this.jogador = jogador;
        this.inimigo = inimigo;
        this.inventario = inventario;
        this.encontro = encontro;
        this.turno = turno;
    }

    public void atacar() throws AcaoInvalidaException {
        // TODO
        throw new UnsupportedOperationException("TODO");
    }

    public void defender() throws AcaoInvalidaException {
        // TODO
        throw new UnsupportedOperationException("TODO");
    }

    public void usarHabilidade() throws AcaoInvalidaException {
        // TODO
        throw new UnsupportedOperationException("TODO");
    }

    /** Em combate gasta o turno; entre encontros, não. */
    public void usarPocao() throws AcaoInvalidaException {
        // TODO
        throw new UnsupportedOperationException("TODO");
    }

    /** Próximo inimigo; recurso do jogador volta ao máximo. */
    public void avancarEncontro() throws AcaoInvalidaException {
        // TODO
        throw new UnsupportedOperationException("TODO");
    }

    /** max(0, 100 x vencidos + (se venceu: 200 + vida) + max(0, 100 - turnos)) */
    public int calcularPontuacao() {
        // TODO
        throw new UnsupportedOperationException("TODO");
    }

    public boolean isVitoria() {
        return encontro == 3 && !inimigo.estaVivo();
    }

    public boolean isDerrota() {
        return !jogador.estaVivo();
    }

    public boolean isEncerrada() {
        return isVitoria() || isDerrota();
    }

    public boolean isEntreEncontros() {
        return encontro < 3 && !inimigo.estaVivo();
    }

    public Guardiao getJogador() { return jogador; }
    public Inimigo getInimigo() { return inimigo; }
    public Inventario getInventario() { return inventario; }
    public int getEncontro() { return encontro; }
    public int getTurno() { return turno; }
    public List<String> getHistorico() { return historico; }
}
