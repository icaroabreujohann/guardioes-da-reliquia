package dominio.personagem.guardiao;

public enum TipoGuardiao {
    ARQUEIRO("Arqueiro"),
    GUERREIRO("Guerreiro"),
    MAGO("Mago");

    private final String descricao;

    TipoGuardiao(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
