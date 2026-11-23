package guardioes.ui;

import javax.swing.JFrame;
import javax.swing.JLabel;

/** Janela inicial: nome do jogador e escolha da classe. */
public class TelaInicio extends JFrame {

    private static final long serialVersionUID = 1L;

    public TelaInicio() {
        super("Guardiões da Relíquia");
        add(new JLabel("Tela inicial (em construção)"));
        // TODO campo de nome, escolha da classe e botão "Começar", que faz:
        //      new TelaCombate(new Partida(Guardiao.criar(classe, nome))).setVisible(true);
        //      dispose();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);
    }
}
