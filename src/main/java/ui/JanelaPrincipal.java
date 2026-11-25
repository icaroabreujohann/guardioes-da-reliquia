package ui;

import dominio.partida.Partida;

import javax.swing.*;
import java.awt.*;

public class JanelaPrincipal extends JFrame {
    private final CardLayout cartoes = new CardLayout();
    private final JPanel conteudo = new JPanel(cartoes);

    public JanelaPrincipal() {
        Tema.aplicar();
        setTitle("Guardiões da Relíquia");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        conteudo.add(new TelaInicio(this), "inicio");
        add(conteudo);
        setSize(1280, 800);
        setLocationRelativeTo(null);
    }

    public void mostrarTela(String nome) {
        cartoes.show(conteudo, nome);
    }

    public void iniciarCombate(Partida partida) { // provisório: troca pela TelaCombate quando ela existir
        String jogador = partida.getJogador().getNome() + " - " + partida.getJogador().getTipo().getDescricao();
        conteudo.add(new JLabel(jogador, SwingConstants.CENTER), "combate");
        mostrarTela("combate");
    }
}