package dominio.partida;

/**
 * Faz a conta dos pontos no fim da partida.
 */
public class CalculadoraPontuacao {
    private static final int PONTOS_POR_ENCONTRO = 100;
    private static final int BONUS_VITORIA = 200;
    private static final int LIMITE_BONUS_TURNOS = 100;

    /**
     * Não precisa criar objeto dessa classe, é só chamar o calcular direto.
     */
    public CalculadoraPontuacao() {
    }

    /**
     * Ganha 100 por encontro vencido, mais 200 + a vida que sobrou se venceu tudo,
     * mais um bônus por terminar rápido (100 - turnos). Nunca fica negativo.
     */
    public static int calcular(int encontrosVencidos, ResultadoPartida resultado, int vidaRestante, int turnos) {
        int pontos = PONTOS_POR_ENCONTRO * encontrosVencidos;
        if (resultado == ResultadoPartida.VITORIA) {
            pontos += BONUS_VITORIA + vidaRestante;
        }
        pontos += Math.max(0, LIMITE_BONUS_TURNOS - turnos); // bônus de turnos não tira ponto
        return Math.max(0, pontos);
    }
}
