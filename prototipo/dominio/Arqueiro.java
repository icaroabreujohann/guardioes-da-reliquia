package guardioes.dominio;

public class Arqueiro extends Guardiao {

    public Arqueiro(String nome) {
        super(nome, 100, 15, 3, 6, 2);     // vida, ataque, defesa, flechas, custo
    }

    /** Tiro Certeiro: ataque x 3 / 2, ignora a defesa. */
    @Override
    protected String aplicarHabilidade(Personagem alvo) {
        // TODO
        throw new UnsupportedOperationException("TODO");
    }

    @Override
    public String getClasse() { return "Arqueiro"; }
}
