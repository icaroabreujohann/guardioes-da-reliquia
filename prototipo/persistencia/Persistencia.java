package guardioes.persistencia;

import guardioes.dominio.Partida;
import guardioes.dominio.RegistroRanking;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

/** Lê e grava o salvamento e o ranking, em texto UTF-8, na pasta escolhida. */
public class Persistencia {

    private final Path pasta;

    public Persistencia(Path pasta) {
        this.pasta = pasta;
    }

    /** Grava em "partida.sav.tmp" e só depois substitui "partida.sav". */
    public void salvarPartida(Partida partida) throws IOException {
        // TODO
        throw new UnsupportedOperationException("TODO");
    }

    /** Valida tudo antes de devolver a Partida. Arquivo inválido = IOException. */
    public Partida carregarPartida() throws IOException {
        // TODO
        throw new UnsupportedOperationException("TODO");
    }

    /** Lista já ordenada. Sem arquivo ainda = lista vazia. */
    public List<RegistroRanking> carregarRanking() throws IOException {
        // TODO
        throw new UnsupportedOperationException("TODO");
    }

    public void registrarNoRanking(RegistroRanking registro) throws IOException {
        // TODO
        throw new UnsupportedOperationException("TODO");
    }

    public Path getPasta() { return pasta; }
}
