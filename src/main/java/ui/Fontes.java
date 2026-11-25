package ui;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.io.InputStream;

public class Fontes {
    // a fonte vai dentro do .jar, então o jogo não depende de a Inter estar instalada no PC
    private static final Font REGULAR = carregar("/fontes/Inter-Regular.ttf");
    private static final Font NEGRITO = carregar("/fontes/Inter-Bold.ttf");
    private static final Font EXTRA_NEGRITO = carregar("/fontes/Inter-ExtraBold.ttf");
    private static final Font ITALICO = carregar("/fontes/Inter-Italic.ttf");

    public static Font normal(float tamanho) {
        return REGULAR.deriveFont(tamanho);
    }

    public static Font negrito(float tamanho) {
        return NEGRITO.deriveFont(tamanho);
    }

    public static Font titulo(float tamanho) {
        return EXTRA_NEGRITO.deriveFont(tamanho);
    }

    public static Font italico(float tamanho) {
        return ITALICO.deriveFont(tamanho);
    }

    private static Font carregar(String caminho) {
        try (InputStream arquivo = Fontes.class.getResourceAsStream(caminho)) {
            if (arquivo == null)
                throw new IllegalStateException("Fonte não encontrada: " + caminho);

            return Font.createFont(Font.TRUETYPE_FONT, arquivo);
        } catch (IOException | FontFormatException e) {
            throw new IllegalStateException("Não foi possível ler a fonte: " + caminho, e);
        }
    }
}
