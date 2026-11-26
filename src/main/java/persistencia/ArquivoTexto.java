package persistencia;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

class ArquivoTexto { // sem public porque só os repositórios deste pacote usam
    private static final String BOM = "﻿";

    static List<String> lerLinhas(Path arquivo) throws PersistenciaException {
        try {
            List<String> linhas = new ArrayList<>(Files.readAllLines(arquivo, StandardCharsets.UTF_8));
            if (!linhas.isEmpty() && linhas.get(0).startsWith(BOM)) // alguns editores do Windows põem esse caractere invisível no começo do arquivo
                linhas.set(0, linhas.get(0).substring(BOM.length()));

            return linhas;
        } catch (IOException e) {
            throw new PersistenciaException("Não foi possível ler o arquivo " + arquivo + ".", e);
        }
    }

    static void gravarComTemporario(Path arquivo, List<String> linhas) throws PersistenciaException {
        Path temporario = arquivo.resolveSibling(arquivo.getFileName() + ".tmp");

        try {
            Files.createDirectories(arquivo.toAbsolutePath().getParent());
            Files.write(temporario, linhas, StandardCharsets.UTF_8);
            Files.move(temporario, arquivo, StandardCopyOption.REPLACE_EXISTING); // só troca o arquivo bom depois que o novo foi gravado inteiro
        } catch (IOException e) {
            throw new PersistenciaException("Não foi possível gravar o arquivo " + arquivo + ".", e);
        }
    }
}
