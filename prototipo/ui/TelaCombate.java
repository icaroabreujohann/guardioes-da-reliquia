package guardioes.ui;

import guardioes.dominio.Partida;

import javax.swing.JFrame;
import javax.swing.JLabel;

/** Janela do combate. Cada botão chama a Partida e depois a tela se atualiza. */
public class TelaCombate extends JFrame {

    private static final long serialVersionUID = 1L;

    private Partida partida;

    public TelaCombate(Partida partida) {
        super("Guardiões da Relíquia");
        this.partida = partida;
        add(new JLabel("Combate de " + partida.getJogador().getNome() + " (em construção)"));
        // TODO status do jogador e do inimigo, encontro, turno, poções, histórico,
        //      botões de ação, nova partida, salvar, carregar, ranking e pasta de dados
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 680);
        setLocationRelativeTo(null);
    }
}
