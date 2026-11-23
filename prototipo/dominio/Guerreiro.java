package guardioes.dominio;

public class Guerreiro extends Guardiao {

    public Guerreiro(String nome) {
        super(nome, 110, 12, 5, 30, 10);   // vida, ataque, defesa, energia, custo
    }

    /** Golpe Poderoso: 2 x ataque - defesa do alvo. */
    @Override
    protected String aplicarHabilidade(Personagem alvo) {
        // TODO
        throw new UnsupportedOperationException("TODO");
    }

    @Override
    public String getClasse() { return "Guerreiro"; }
}
