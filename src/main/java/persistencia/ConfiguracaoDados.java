package persistencia;

import java.nio.file.Path;

public class ConfiguracaoDados {
    private static final String NOME_PASTA_PADRAO = "GuardioesDaReliquia";
    private static final String ARQUIVO_SALVAMENTO = "partida.sav";
    private static final String ARQUIVO_RANKING = "ranking.txt";

    private Path pastaDados;

    public ConfiguracaoDados() {
        this(Path.of(System.getProperty("user.home"), NOME_PASTA_PADRAO)); // começa na pasta do usuário até ele escolher outra
    }

    public ConfiguracaoDados(Path pastaDados) { // os testes passam aqui um diretório temporário
        definirPasta(pastaDados);
    }

    public void definirPasta(Path pasta) {
        if (pasta == null)
            throw new IllegalArgumentException("A pasta de dados é obrigatória.");

        pastaDados = pasta;
    }

    public Path getPastaDados() {
        return pastaDados;
    }

    public Path getArquivoSalvamento() {
        return pastaDados.resolve(ARQUIVO_SALVAMENTO); // resolve monta o caminho sem concatenar texto, então espaços e acentos não atrapalham
    }

    public Path getArquivoRanking() {
        return pastaDados.resolve(ARQUIVO_RANKING);
    }
}
