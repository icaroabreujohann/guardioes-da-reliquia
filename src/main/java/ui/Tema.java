package ui;

import javax.swing.*;
import java.awt.*;

public class Tema {
    public static final Color DOURADO = new Color(240, 192, 80);
    public static final Color TEXTO = new Color(232, 226, 214);
    public static final Color TEXTO_APAGADO = new Color(120, 112, 132);
    public static final Color BORDA_APAGADA = new Color(90, 80, 112);
    public static final Color FUNDO_ESCURO = new Color(28, 20, 42);

    /**
     * Define a Inter como fonte padrão dos componentes do Swing. Precisa ser chamado antes de criar as telas.
     */
    public static void aplicar() {
        Font texto = Fontes.normal(15f);
        Font destaque = Fontes.negrito(15f);
        UIManager.put("Label.font", texto);
        UIManager.put("TextField.font", texto);
        UIManager.put("Button.font", destaque);
        UIManager.put("OptionPane.messageFont", texto);
        UIManager.put("OptionPane.buttonFont", destaque);
        UIManager.put("Button.disabledText", TEXTO_APAGADO);
    }
}
