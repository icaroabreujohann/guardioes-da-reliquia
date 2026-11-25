package ui;

import dominio.personagem.FabricaPersonagens;
import dominio.personagem.guardiao.Guardiao;
import dominio.personagem.guardiao.TipoGuardiao;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class CartaoClasse extends JPanel {
    private static final int ESCALA_SPRITE = 3;
    private static final Color FUNDO = new Color(14, 10, 24, 210);
    private static final Border BORDA_NORMAL = BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Tema.BORDA_APAGADA, 2),
            BorderFactory.createEmptyBorder(14, 18, 14, 18));
    private static final Border BORDA_SELECIONADA = BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Tema.DOURADO, 4),
            BorderFactory.createEmptyBorder(12, 16, 12, 16));

    private final TipoGuardiao tipo;

    public CartaoClasse(TipoGuardiao tipo, TelaInicio tela) {
        this.tipo = tipo;
        Guardiao previa = FabricaPersonagens.criarGuardiao(tipo, tipo.getDescricao()); // só para ler os atributos: os valores continuam só no domínio

        setOpaque(false);
        setLayout(new BorderLayout(0, 10));
        setBorder(BORDA_NORMAL);
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        add(criarTitulo(), BorderLayout.NORTH);
        add(criarSprite(), BorderLayout.CENTER);
        add(criarAtributos(previa), BorderLayout.SOUTH);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                tela.selecionar(CartaoClasse.this);
            }
        });
    }

    private JLabel criarTitulo() {
        JLabel titulo = new JLabel(tipo.getDescricao(), SwingConstants.CENTER);
        titulo.setFont(Fontes.negrito(26f));
        titulo.setForeground(Tema.DOURADO);
        return titulo;
    }

    private JLabel criarSprite() {
        BufferedImage sprite = Imagens.carregar("/sprites/" + tipo.name().toLowerCase() + ".png");
        Image ampliado = sprite.getScaledInstance(sprite.getWidth() * ESCALA_SPRITE,
                sprite.getHeight() * ESCALA_SPRITE, Image.SCALE_REPLICATE); // REPLICATE mantém o pixel art nítido
        return new JLabel(new ImageIcon(ampliado));
    }

    private JPanel criarAtributos(Guardiao previa) {
        JPanel atributos = new JPanel(new GridLayout(0, 2, 10, 4));
        atributos.setOpaque(false);
        adicionarAtributo(atributos, "Vida", String.valueOf(previa.getVidaMaxima()));
        adicionarAtributo(atributos, "Ataque", String.valueOf(previa.getAtaque()));
        adicionarAtributo(atributos, "Defesa", String.valueOf(previa.getDefesa()));
        adicionarAtributo(atributos, previa.getNomeRecurso(), String.valueOf(previa.getRecursoMaximo()));
        adicionarAtributo(atributos, "Habilidade", previa.getNomeHabilidade());
        return atributos;
    }

    private void adicionarAtributo(JPanel painel, String nome, String valor) {
        JLabel rotulo = new JLabel(nome);
        rotulo.setFont(Fontes.normal(15f));
        rotulo.setForeground(Tema.TEXTO);

        JLabel conteudo = new JLabel(valor, SwingConstants.RIGHT);
        conteudo.setFont(Fontes.negrito(15f));
        conteudo.setForeground(Tema.DOURADO);

        painel.add(rotulo);
        painel.add(conteudo);
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(FUNDO);
        g.fillRect(0, 0, getWidth(), getHeight());
        super.paintComponent(g);
    }

    public void setSelecionado(boolean selecionado) {
        setBorder(selecionado ? BORDA_SELECIONADA : BORDA_NORMAL);
    }

    public TipoGuardiao getTipo() {
        return tipo;
    }
}
