package dominio.partida;

import dominio.AcaoInvalidaException;
import dominio.item.Inventario;
import dominio.personagem.FabricaPersonagens;
import dominio.personagem.guardiao.Guardiao;
import dominio.personagem.inimigo.Inimigo;

import java.util.ArrayList;
import java.util.List;

public class Partida {
    private static final int TOTAL_ENCONTROS = 3;

    private final Guardiao jogador;
    private Inimigo inimigo;
    private final Inventario inventario;
    private int encontroAtual;
    private int turnoEncontro;
    private int turnoTotal;
    private int descansosUsados;
    private ResultadoPartida resultado;
    private boolean aguardandoProximoEncontro;
    private final List<String> historico;

    public Partida(Guardiao jogador) {
        if (jogador == null)
            throw new IllegalArgumentException("O jogador é obrigatório.");

        this.jogador = jogador;
        this.inventario = new Inventario();
        this.encontroAtual = 1;
        this.inimigo = FabricaPersonagens.criarInimigoDoEncontro(encontroAtual);
        this.resultado = ResultadoPartida.EM_ANDAMENTO;
        this.aguardandoProximoEncontro = false;
        this.historico = new ArrayList<>();
        registrarInicioDoEncontro();
    }


    public void executarRodada(TipoAcao acao) {
        exigirEmCombate();
        String efeitoDoJogador = executarAcaoDoJogador(acao); // se a ação for inválida, a exceção sai aqui, antes de qualquer contador mudar

        turnoEncontro++;
        turnoTotal++;
        historico.add(efeitoDoJogador);

        if (!inimigo.estaVivo()) {
            encerrarEncontro();
            return;
        }

        historico.add(inimigo.agir(jogador));
        if (!jogador.estaVivo()) {
            resultado = ResultadoPartida.DERROTA;
            historico.add(jogador.getNome() + " foi derrotado. Fim de jogo.");
        }
    }

    public void usarPocaoEntreEncontros() {
        exigirEntreEncontros();
        int curado = inventario.usarPocao(jogador);
        historico.add(jogador.getNome() + " usou uma poção e recuperou " + curado + " de vida.");
    }

    public void descansar() {
        exigirEntreEncontros();
        if (jogador.getVida() == jogador.getVidaMaxima()) // evita gastar um descanso (e perder pontos) sem ganhar nada
            throw new AcaoInvalidaException("A vida já está cheia.");

        int curado = jogador.curar(jogador.getVidaMaxima());
        descansosUsados++;
        historico.add(jogador.getNome() + " descansou e recuperou " + curado + " de vida.");
    }

    public void avancarEncontro() {
        exigirEntreEncontros();
        encontroAtual++;
        inimigo = FabricaPersonagens.criarInimigoDoEncontro(encontroAtual);
        turnoEncontro = 0;
        jogador.recuperarRecurso();
        aguardandoProximoEncontro = false;
        registrarInicioDoEncontro();
    }

    public int getPontuacao() {
        return CalculadoraPontuacao.calcular(getEncontrosVencidos(), resultado, jogador.getVida(), turnoTotal);
    }

    public boolean isEncerrada() {
        return resultado != ResultadoPartida.EM_ANDAMENTO;
    }

    public int getEncontrosVencidos() {
        if (resultado == ResultadoPartida.VITORIA)
            return TOTAL_ENCONTROS;

        if (aguardandoProximoEncontro)
            return encontroAtual;

        return encontroAtual - 1; // em combate ou derrotado, o encontro atual ainda não foi vencido
    }

    private String executarAcaoDoJogador(TipoAcao acao) {
        if (acao == null)
            throw new IllegalArgumentException("A ação é obrigatória.");

        return switch (acao) {
            case ATACAR -> atacar();
            case DEFENDER -> defender();
            case HABILIDADE -> jogador.usarHabilidade(inimigo);
            case POCAO -> usarPocaoEmCombate();
        };
    }

    private String atacar() {
        int dano = jogador.atacar(inimigo);
        return jogador.getNome() + " atacou " + inimigo.getNome() + " e causou " + dano + " de dano.";
    }

    private String defender() {
        jogador.defender();
        return jogador.getNome() + " se defendeu. O próximo dano recebido cai pela metade.";
    }

    private String usarPocaoEmCombate() {
        int curado = inventario.usarPocao(jogador);
        return jogador.getNome() + " usou uma poção e recuperou " + curado + " de vida.";
    }

    private void encerrarEncontro() {
        historico.add(inimigo.getNome() + " foi derrotado.");

        if (encontroAtual == TOTAL_ENCONTROS) {
            resultado = ResultadoPartida.VITORIA;
            historico.add(jogador.getNome() + " recuperou a relíquia!");
        } else {
            inventario.adicionarPocao(); // só os encontros 1 e 2 caem aqui, que são os que dão a poção extra
            historico.add(jogador.getNome() + " encontrou uma poção.");
            aguardandoProximoEncontro = true;
        }
    }

    private void registrarInicioDoEncontro() {
        historico.add("Encontro " + encontroAtual + ": " + inimigo.getNome() + " apareceu.");
    }

    private void exigirEmCombate() {
        exigirNaoEncerrada();
        if (aguardandoProximoEncontro)
            throw new AcaoInvalidaException("O encontro acabou. Avance para o próximo.");
    }

    private void exigirEntreEncontros() {
        exigirNaoEncerrada();
        if (!aguardandoProximoEncontro)
            throw new AcaoInvalidaException("Isso só pode ser feito entre encontros.");
    }

    private void exigirNaoEncerrada() {
        if (isEncerrada())
            throw new AcaoInvalidaException("A partida já terminou.");
    }

    public Guardiao getJogador() {
        return jogador;
    }

    public Inimigo getInimigo() {
        return inimigo;
    }

    public Inventario getInventario() {
        return inventario;
    }

    public int getEncontroAtual() {
        return encontroAtual;
    }

    public int getTurnoEncontro() {
        return turnoEncontro;
    }

    public int getTurnoTotal() {
        return turnoTotal;
    }

    public int getDescansosUsados() {
        return descansosUsados;
    }

    public ResultadoPartida getResultado() {
        return resultado;
    }

    public boolean isAguardandoProximoEncontro() {
        return aguardandoProximoEncontro;
    }

    public List<String> getHistorico() {
        return new ArrayList<>(historico); // devolve uma cópia para a interface não alterar o histórico
    }
}
