package ui;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class Imagens {

    public static BufferedImage carregar(String caminho) {
        URL url = Imagens.class.getResource(caminho);
        if (url == null)
            throw new IllegalStateException("Imagem não encontrada: " + caminho);

        try {
            return ImageIO.read(url);
        } catch (IOException e) {
            throw new IllegalStateException("Não foi possível ler a imagem: " + caminho, e);
        }
    }
}
