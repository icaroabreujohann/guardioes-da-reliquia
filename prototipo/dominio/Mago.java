package guardioes.dominio;

public class Mago extends Guardiao {

    public Mago(String nome) {
        super(nome, 90, 8, 2, 45, 15);     // vida, ataque, defesa, mana, custo
    }

    /** Bola de Fogo: 35 de dano, ignora a defesa. */
    @Override
    protected String aplicarHabilidade(Personagem alvo) {
        // TODO
        throw new UnsupportedOperationException("TODO");
    }

    @Override
    public String getClasse() { return "Mago"; }
}
