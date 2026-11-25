package ui;

import javax.swing.*;
import java.awt.*;

public class BotaoJogo extends JButton {
    private static final Color FUNDO = new Color(28, 20, 42, 230);
    private static final Color FUNDO_HOVER = new Color(66, 48, 90, 240);
    private static final Color FUNDO_PRESSIONADO = new Color(16, 10, 26, 240);
    private static final Color FUNDO_DESABILITADO = new Color(30, 26, 38, 170);
    private static final Color BRILHO = new Color(255, 240, 190, 90);

    public BotaoJogo(String texto) {
        super(texto);
        setFont(Fontes.negrito(18f));
        setForeground(Tema.TEXTO);
        setFocusPainted(false);
        setContentAreaFilled(false); // fundo e borda são desenhados no paintComponent
        setBorderPainted(false);
        setOpaque(false);
        setBorder(BorderFactory.createEmptyBorder(10, 28, 10, 28));
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    @Override
    protected void paintComponent(Graphics g) {
        int largura = getWidth();
        int altura = getHeight();

        g.setColor(corDoFundo());
        g.fillRect(0, 0, largura, altura);

        g.setColor(isEnabled() ? Tema.DOURADO : Tema.BORDA_APAGADA);
        g.drawRect(0, 0, largura - 1, altura - 1);
        g.drawRect(1, 1, largura - 3, altura - 3);

        if (isEnabled() && !getModel().isPressed()) {
            g.setColor(BRILHO);
            g.drawLine(3, 3, largura - 4, 3);
        }
        super.paintComponent(g); // desenha o texto por cima
    }

    private Color corDoFundo() {
        if (!isEnabled())
            return FUNDO_DESABILITADO;

        if (getModel().isPressed())
            return FUNDO_PRESSIONADO;

        if (getModel().isRollover())
            return FUNDO_HOVER;

        return FUNDO;
    }
}
