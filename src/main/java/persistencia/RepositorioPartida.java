package persistencia;

import dominio.item.Inventario;
import dominio.partida.Partida;
import dominio.personagem.FabricaPersonagens;
import dominio.personagem.guardiao.Guardiao;
import dominio.personagem.guardiao.TipoGuardiao;
import dominio.personagem.inimigo.Inimigo;
import dominio.personagem.inimigo.TipoInimigo;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RepositorioPartida {
    private static final String CABECALHO = "GUARDIOES-PARTIDA;1";
    private static final String SEPARADOR = "=";

    private static final String NOME = "nome";
    private static final String CLASSE = "classe";
    private static final String VIDA = "vida";
    private static final String DEFENDENDO = "defendendo";
    private static final String RECURSO = "recurso";
    private static final String POCOES = "pocoes";
    private static final String ENCONTRO = "encontro";
    private static final String TURNO_ENCONTRO = "turnoEncontro";
    private static final String TURNO_TOTAL = "turnoTotal";
    private static final String DESCANSOS = "descansos";
    private static final String ENTRE_ENCONTROS = "entreEncontros";
    private static final String INIMIGO = "inimigo";
    private static final String VIDA_INIMIGO = "vidaInimigo";
    private static final String ESTADO_INIMIGO = "estadoEspecialInimigo";

    private final ConfiguracaoDados configuracao;

    public RepositorioPartida(ConfiguracaoDados configuracao) {
        if (configuracao == null)
            throw new IllegalArgumentException("A configuração de dados é obrigatória.");

        this.configuracao = configuracao;
    }

    public boolean existeSalvamento() {
        return new ArquivoTexto(configuracao.getArquivoSalvamento()).existe();
    }

    public void apagarSalvamento() throws PersistenciaException { // chamado quando a partida termina, para não dar para carregar e refazer o final
        new ArquivoTexto(configuracao.getArquivoSalvamento()).apagar();
    }

    public void salvar(Partida partida) throws PersistenciaException {
        if (partida == null)
            throw new IllegalArgumentException("A partida não pode ser nula.");

        if (partida.isEncerrada())
            throw new PersistenciaException("Partida encerrada não pode ser salva. O resultado vai para o ranking.");

        Guardiao jogador = partida.getJogador();
        Inimigo inimigo = partida.getInimigo();

        List<String> linhas = new ArrayList<>();
        linhas.add(CABECALHO);
        linhas.add(campo(NOME, jogador.getNome()));
        linhas.add(campo(CLASSE, jogador.getTipo().name()));
        linhas.add(campo(VIDA, jogador.getVida()));
        linhas.add(campo(DEFENDENDO, jogador.isDefendendo()));
        linhas.add(campo(RECURSO, jogador.getRecurso()));
        linhas.add(campo(POCOES, partida.getInventario().getPocoes()));
        linhas.add(campo(ENCONTRO, partida.getEncontroAtual()));
        linhas.add(campo(TURNO_ENCONTRO, partida.getTurnoEncontro()));
        linhas.add(campo(TURNO_TOTAL, partida.getTurnoTotal()));
        linhas.add(campo(DESCANSOS, partida.getDescansosUsados()));
        linhas.add(campo(ENTRE_ENCONTROS, partida.isAguardandoProximoEncontro()));
        linhas.add(campo(INIMIGO, inimigo.getTipo().name()));
        linhas.add(campo(VIDA_INIMIGO, inimigo.getVida()));
        linhas.add(campo(ESTADO_INIMIGO, inimigo.getEstadoEspecial()));

        new ArquivoTexto(configuracao.getArquivoSalvamento()).gravarComTemporario(linhas);
    }

    public Partida carregar() throws PersistenciaException {
        Path caminho = configuracao.getArquivoSalvamento();
        ArquivoTexto arquivo = new ArquivoTexto(caminho); // cria na hora porque o usuário pode ter trocado a pasta

        if (!arquivo.existe())
            throw new PersistenciaException("Não há partida salva em " + configuracao.getPastaDados() + ".");

        List<String> linhas = arquivo.lerLinhas();

        if (linhas.isEmpty() || !linhas.get(0).equals(CABECALHO))
            throw new PersistenciaException("O arquivo de salvamento não está no formato esperado: " + caminho);

        Map<String, String> campos = separarCampos(linhas);

        try {
            return montarPartida(campos); // tudo é criado do zero, então se algo falhar a partida em uso não é tocada
        } catch (IllegalArgumentException e) { // vem do valueOf dos enums e das validações do domínio
            throw new PersistenciaException("O salvamento tem dados inconsistentes: " + e.getMessage(), e);
        }
    }

    private Partida montarPartida(Map<String, String> campos) throws PersistenciaException {
        TipoGuardiao classe = TipoGuardiao.valueOf(lerTexto(campos, CLASSE));
        Guardiao jogador = FabricaPersonagens.criarGuardiao(classe, lerTexto(campos, NOME));
        jogador.restaurarEstado(lerInteiro(campos, VIDA), lerBooleano(campos, DEFENDENDO));
        jogador.restaurarRecurso(lerInteiro(campos, RECURSO));

        TipoInimigo tipoInimigo = TipoInimigo.valueOf(lerTexto(campos, INIMIGO));
        Inimigo inimigo = FabricaPersonagens.criarInimigoPorTipo(tipoInimigo);
        inimigo.restaurarEstado(lerInteiro(campos, VIDA_INIMIGO), false); // os inimigos nunca se defendem, então isso não vai para o arquivo
        inimigo.restaurarEstadoEspecial(lerInteiro(campos, ESTADO_INIMIGO));

        Inventario inventario = new Inventario(lerInteiro(campos, POCOES));

        return new Partida(jogador, inimigo, inventario, lerInteiro(campos, ENCONTRO), lerInteiro(campos, TURNO_ENCONTRO),
                lerInteiro(campos, TURNO_TOTAL), lerInteiro(campos, DESCANSOS), lerBooleano(campos, ENTRE_ENCONTROS));
    }

    private Map<String, String> separarCampos(List<String> linhas) throws PersistenciaException {
        Map<String, String> campos = new HashMap<>();

        for (int i = 1; i < linhas.size(); i++) {
            String[] partes = linhas.get(i).split(SEPARADOR, 2); // corta só no primeiro "=", então um "=" no nome não atrapalha
            if (partes.length != 2)
                throw new PersistenciaException("A linha " + (i + 1) + " do salvamento não está no formato chave=valor.");

            if (campos.put(partes[0], partes[1]) != null) // o put devolve o valor anterior, então não nulo quer dizer campo repetido
                throw new PersistenciaException("O campo " + partes[0] + " aparece mais de uma vez no salvamento.");
        }
        return campos;
    }

    private String lerTexto(Map<String, String> campos, String chave) throws PersistenciaException {
        String valor = campos.get(chave);
        if (valor == null)
            throw new PersistenciaException("Falta o campo " + chave + " no salvamento.");

        return valor;
    }

    private int lerInteiro(Map<String, String> campos, String chave) throws PersistenciaException {
        String valor = lerTexto(campos, chave);

        try {
            return Integer.parseInt(valor);
        } catch (NumberFormatException e) {
            throw new PersistenciaException("O campo " + chave + " deveria ser um número, mas é \"" + valor + "\".", e);
        }
    }

    private boolean lerBooleano(Map<String, String> campos, String chave) throws PersistenciaException {
        String valor = lerTexto(campos, chave);
        if (!valor.equals("true") && !valor.equals("false")) // o Boolean.parseBoolean aceitaria qualquer texto como false
            throw new PersistenciaException("O campo " + chave + " deveria ser true ou false, mas é \"" + valor + "\".");

        return valor.equals("true");
    }

    private String campo(String chave, Object valor) {
        return chave + SEPARADOR + valor;
    }
}
