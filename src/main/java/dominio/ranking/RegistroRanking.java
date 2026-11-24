package dominio.ranking;

import dominio.enums.ResultadoPartida;
import dominio.enums.TipoGuardiao;

import java.time.LocalDateTime;

public class RegistroRanking {
    private final String nome;
    private final TipoGuardiao classe;
    private final int pontuacao;
    private final ResultadoPartida resultado;
    private final int turnos;
    private final LocalDateTime data;

    public RegistroRanking(String nome, TipoGuardiao classe, int pontuacao, ResultadoPartida resultado,
                           int turnos, LocalDateTime data) {
        if (nome == null || nome.isBlank())
            throw new IllegalArgumentException("O nome do jogador não pode ser vazio.");

        if (classe == null)
            throw new IllegalArgumentException("A classe do guardião é obrigatória.");

        if (pontuacao < 0)
            throw new IllegalArgumentException("Pontuação inválida: " + pontuacao);

        if (resultado == null || resultado == ResultadoPartida.EM_ANDAMENTO) // partida em andamento ainda não tem pontuação final
            throw new IllegalArgumentException("Só entra no ranking partida com vitória ou derrota.");

        if (turnos < 0)
            throw new IllegalArgumentException("Quantidade de turnos inválida: " + turnos);

        if (data == null)
            throw new IllegalArgumentException("A data do registro é obrigatória.");

        this.nome = nome;
        this.classe = classe;
        this.pontuacao = pontuacao;
        this.resultado = resultado;
        this.turnos = turnos;
        this.data = data;
    }

    public boolean vemAntes(RegistroRanking outro) {
        if (pontuacao != outro.pontuacao)
            return pontuacao > outro.pontuacao; // quem fez mais pontos fica na frente

        return turnos < outro.turnos; // no empate, leva vantagem quem precisou de menos turnos
    }

    public String getNome() {
        return nome;
    }

    public TipoGuardiao getClasse() {
        return classe;
    }

    public int getPontuacao() {
        return pontuacao;
    }

    public ResultadoPartida getResultado() {
        return resultado;
    }

    public int getTurnos() {
        return turnos;
    }

    public LocalDateTime getData() {
        return data;
    }
}
