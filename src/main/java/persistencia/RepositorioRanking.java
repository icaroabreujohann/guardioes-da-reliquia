package persistencia;

import dominio.partida.ResultadoPartida;
import dominio.personagem.guardiao.TipoGuardiao;
import dominio.ranking.Ranking;
import dominio.ranking.RegistroRanking;

import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class RepositorioRanking {
    private static final String CABECALHO = "GUARDIOES-RANKING;1";
    private static final String SEPARADOR = ";";
    private static final int CAMPOS_POR_LINHA = 6;

    private final ConfiguracaoDados configuracao;

    public RepositorioRanking(ConfiguracaoDados configuracao) {
        if (configuracao == null)
            throw new IllegalArgumentException("A configuração de dados é obrigatória.");

        this.configuracao = configuracao;
    }

    public Ranking carregar() throws PersistenciaException {
        Path arquivo = configuracao.getArquivoRanking();

        if (!Files.exists(arquivo))
            return new Ranking(); // na primeira vez ainda não há arquivo, e isso não é erro

        List<String> linhas = ArquivoTexto.lerLinhas(arquivo);

        if (linhas.isEmpty() || !linhas.get(0).equals(CABECALHO))
            throw new PersistenciaException("O arquivo de ranking não está no formato esperado: " + arquivo);

        Ranking ranking = new Ranking(); // monta um ranking novo, então um arquivo ruim não estraga nada que já esteja em uso
        for (int i = 1; i < linhas.size(); i++)
            ranking.adicionar(converterLinha(linhas.get(i), i + 1));

        return ranking;
    }

    public void registrar(RegistroRanking registro) throws PersistenciaException {
        if (registro == null)
            throw new IllegalArgumentException("O registro não pode ser nulo.");

        if (registro.getNome().contains(SEPARADOR))
            throw new PersistenciaException("O nome do jogador não pode conter \"" + SEPARADOR + "\".");

        Ranking ranking = carregar(); // relê o arquivo para não perder os registros gravados antes
        ranking.adicionar(registro);

        List<String> linhas = new ArrayList<>();
        linhas.add(CABECALHO);
        for (RegistroRanking registroOrdenado : ranking.getOrdenado())
            linhas.add(converterRegistro(registroOrdenado));

        ArquivoTexto.gravarComTemporario(configuracao.getArquivoRanking(), linhas);
    }

    private RegistroRanking converterLinha(String linha, int numeroLinha) throws PersistenciaException {
        String[] campos = linha.split(SEPARADOR, -1); // o -1 mantém os campos vazios do fim, para a contagem pegar linha incompleta

        if (campos.length != CAMPOS_POR_LINHA)
            throw new PersistenciaException("A linha " + numeroLinha + " do ranking tem " + campos.length
                    + " campos, eram esperados " + CAMPOS_POR_LINHA + ".");

        try {
            return new RegistroRanking(campos[0], TipoGuardiao.valueOf(campos[1]), Integer.parseInt(campos[2]),
                    ResultadoPartida.valueOf(campos[3]), Integer.parseInt(campos[4]), LocalDateTime.parse(campos[5]));
        } catch (IllegalArgumentException | DateTimeParseException e) { // valueOf e parseInt lançam IllegalArgumentException, e o construtor do registro também
            throw new PersistenciaException("A linha " + numeroLinha + " do ranking é inválida: " + e.getMessage(), e);
        }
    }

    private String converterRegistro(RegistroRanking registro) {
        return String.join(SEPARADOR, registro.getNome(), registro.getClasse().name(), // grava o name() do enum, que é o que o valueOf sabe ler de volta
                String.valueOf(registro.getPontuacao()), registro.getResultado().name(),
                String.valueOf(registro.getTurnos()), registro.getData().truncatedTo(ChronoUnit.SECONDS).toString()); // sem as frações de segundo, que só poluem o arquivo
    }
}
