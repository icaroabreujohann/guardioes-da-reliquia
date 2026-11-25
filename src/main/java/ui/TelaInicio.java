package ui;

import dominio.partida.Partida;
import dominio.personagem.FabricaPersonagens;
import dominio.personagem.guardiao.Guardiao;
import dominio.personagem.guardiao.TipoGuardiao;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class TelaInicio extends JPanel {
    private final JanelaPrincipal janela;
    private final BufferedImage fundo = Imagens.carregar("/cenarios/inicio.png");
    private final JTextField campoNome = new JTextField(16);
    private final List<CartaoClasse> cartoes = new ArrayList<>();
    private TipoGuardiao tipoSelecionado;

    public TelaInicio(JanelaPrincipal janela) {
        this.janela = janela;
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(24, 0, 30, 0));

        add(criarTitulo(), BorderLayout.NORTH);
        add(criarCartoes(), BorderLayout.CENTER);
        add(criarRodape(), BorderLayout.SOUTH);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(fundo, 0, 0, getWidth(), getHeight(), null);
    }

    private JPanel criarTitulo() {
        JLabel titulo = new JLabel("Guardiões da Relíquia", SwingConstants.CENTER);
        titulo.setFont(Fontes.titulo(60f));
        titulo.setForeground(Tema.DOURADO);

        JLabel subtitulo = new JLabel("Escolha o seu guardião", SwingConstants.CENTER);
        subtitulo.setFont(Fontes.italico(22f));
        subtitulo.setForeground(Tema.TEXTO);

        JPanel painel = new JPanel(new GridLayout(2, 1));
        painel.setOpaque(false);
        painel.add(titulo);
        painel.add(subtitulo);
        return painel;
    }

    private JPanel criarCartoes() {
        JPanel linha = new JPanel(new GridLayout(1, 0, 36, 0)); // GridLayout deixa os cartões com a mesma largura
        linha.setOpaque(false);

        for (TipoGuardiao tipo : TipoGuardiao.values()) {
            CartaoClasse cartao = new CartaoClasse(tipo, this);
            cartoes.add(cartao);
            linha.add(cartao);
        }
        selecionar(cartoes.get(0));

        JPanel centro = new JPanel(new GridBagLayout()); // centraliza a linha de cartões na vertical
        centro.setOpaque(false);
        centro.add(linha);
        return centro;
    }

    private JPanel criarRodape() {
        JLabel rotuloNome = new JLabel("Nome:");
        rotuloNome.setFont(Fontes.negrito(18f));
        rotuloNome.setForeground(Tema.TEXTO);
        estilizarCampoNome();

        JButton botaoNovoJogo = new BotaoJogo("Novo Jogo");
        JButton botaoCarregar = new BotaoJogo("Carregar");
        JButton botaoRanking = new BotaoJogo("Ranking");
        botaoNovoJogo.addActionListener(e -> iniciarNovoJogo());
        botaoCarregar.setEnabled(false); // habilitar quando a persistência estiver pronta
        botaoRanking.setEnabled(false);

        JPanel rodape = new JPanel(new FlowLayout(FlowLayout.CENTER, 14, 0));
        rodape.setOpaque(false);
        rodape.add(rotuloNome);
        rodape.add(campoNome);
        rodape.add(botaoNovoJogo);
        rodape.add(botaoCarregar);
        rodape.add(botaoRanking);
        return rodape;
    }

    private void estilizarCampoNome() {
        campoNome.setFont(Fontes.normal(17f));
        campoNome.setBackground(Tema.FUNDO_ESCURO);
        campoNome.setForeground(Tema.TEXTO);
        campoNome.setCaretColor(Tema.DOURADO);
        campoNome.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Tema.DOURADO, 2),
                BorderFactory.createEmptyBorder(8, 10, 8, 10)));
    }

    public void selecionar(CartaoClasse escolhido) {
        for (CartaoClasse cartao : cartoes)
            cartao.setSelecionado(cartao == escolhido);

        tipoSelecionado = escolhido.getTipo();
    }

    private void iniciarNovoJogo() {
        try {
            Guardiao guardiao = FabricaPersonagens.criarGuardiao(tipoSelecionado, campoNome.getText().trim());
            janela.iniciarCombate(new Partida(guardiao));
        } catch (IllegalArgumentException ex) { // a regra do nome fica no Personagem; a tela só mostra o aviso
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Atenção", JOptionPane.WARNING_MESSAGE);
        }
    }
}
