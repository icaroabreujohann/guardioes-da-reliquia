package dominio.personagem;

public class Guardiao extends Personagem {
    private int recurso;
    private int recursoMaximo;
    private int custoHabilidade;

    public Guardiao(String nome, int vidaMaxima, int ataque, int defesa, int recurso, int recursoMaximo, int custoHabilidade) {
        super(nome, vidaMaxima, ataque, defesa);
        this.recurso = recurso;
        this.recursoMaximo = recursoMaximo;
        this.custoHabilidade = custoHabilidade;
    }

    
}
