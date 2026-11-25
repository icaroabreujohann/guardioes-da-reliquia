package dominio.partida;


public class CalculadoraPontuacao {
    private static final int PONTOS_POR_ENCONTRO = 100;
    private static final int BONUS_VITORIA = 200;
    private static final int LIMITE_BONUS_TURNOS = 100;

    public CalculadoraPontuacao() {
    }

    public static int calcular(int encontrosVencidos, ResultadoPartida resultado, int vidaRestante, int turnos) {
        int pontos = PONTOS_POR_ENCONTRO * encontrosVencidos;

        if (resultado == ResultadoPartida.VITORIA)
            pontos += BONUS_VITORIA + vidaRestante; // quem vence tudo também ganha a vida que sobrou

        pontos += Math.max(0, LIMITE_BONUS_TURNOS - turnos); // terminar rápido dá bônus, mas demorar não tira pontos
        return Math.max(0, pontos);
    }
}
