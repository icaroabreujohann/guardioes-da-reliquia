package dominio.personagem;

public class Arqueiro extends Guardiao {
    private static final int VIDA_MAXIMA = 100;
    private static final int ATAQUE = 15;
    private static final int DEFESA = 3;
    private static final int RECURSO_MAXIMO = 6;
    private static final int CUSTO_HABILIDADE = 2;

    public Arqueiro(String nome) {
        super(nome, VIDA_MAXIMA, ATAQUE, DEFESA, RECURSO_MAXIMO, CUSTO_HABILIDADE);
    }

    @Override
    protected int aplicarHabilidade(Personagem alvo) {
        return alvo.receberDanoDireto(ATAQUE * 3 / 2); //Tiro Certeiro: 1,5 × ataque, ignora o ATRIBUTO    defesa
    }

    @Override
    public String getNomeClasse() {
        return "Arqueiro";
    }

    @Override
    public String getNomeRecurso() {
        return "Flechas";
    }

    @Override
    public String getNomeHabilidade() {
        return "Tiro Certeiro";
    }
}