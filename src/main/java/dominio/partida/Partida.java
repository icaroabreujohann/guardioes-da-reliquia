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

    public Partida(Guardiao jogador, Inimigo inimigo, Inventario inventario, int encontroAtual, int turnoEncontro,
                   int turnoTotal, int descansosUsados, boolean aguardandoProximoEncontro) { // usado ao carregar, por isso confere tudo antes de guardar
        if (jogador == null || inimigo == null || inventario == null)
            throw new IllegalArgumentException("Jogador, inimigo e inventário são obrigatórios.");

        if (encontroAtual < 1 || encontroAtual > TOTAL_ENCONTROS)
            throw new IllegalArgumentException("Encontro inválido: " + encontroAtual);

        if (inimigo.getTipo() != FabricaPersonagens.criarInimigoDoEncontro(encontroAtual).getTipo())
            throw new IllegalArgumentException(inimigo.getNome() + " não é o inimigo do encontro " + encontroAtual + ".");

        if (turnoEncontro < 0 || turnoTotal < turnoEncontro || descansosUsados < 0)
            throw new IllegalArgumentException("Contadores de turno ou descanso inválidos.");

        if (!jogador.estaVivo())
            throw new IllegalArgumentException("Partida encerrada não pode ser restaurada.");

        if (aguardandoProximoEncontro == inimigo.estaVivo()) // entre encontros o inimigo tem que estar derrotado, e em combate vivo
            throw new IllegalArgumentException("A vida do inimigo não combina com a fase da partida.");

        if (aguardandoProximoEncontro && encontroAtual == TOTAL_ENCONTROS) // vencer o último encontro já é vitória
            throw new IllegalArgumentException("Partida encerrada não pode ser restaurada.");

        int encontrosVencidos = aguardandoProximoEncontro ? encontroAtual : encontroAtual - 1; // mesma conta do getEncontrosVencidos, que ainda não pode ser chamado aqui
        if (inventario.getPocoes() > Inventario.getPocoesIniciais() + encontrosVencidos) // cada encontro vencido dá só uma poção a mais
            throw new IllegalArgumentException("Quantidade de poções maior do que o possível: " + inventario.getPocoes());

        this.jogador = jogador;
        this.inimigo = inimigo;
        this.inventario = inventario;
        this.encontroAtual = encontroAtual;
        this.turnoEncontro = turnoEncontro;
        this.turnoTotal = turnoTotal;
        this.descansosUsados = descansosUsados;
        this.resultado = ResultadoPartida.EM_ANDAMENTO;
        this.aguardandoProximoEncontro = aguardandoProximoEncontro;
        this.historico = new ArrayList<>();
        historico.add("Partida carregada: encontro " + encontroAtual + ", turno " + turnoEncontro + ".");
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
