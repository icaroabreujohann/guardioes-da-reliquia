package dominio.enums;

public enum TipoInimigo {
    FEITICEIRO("Feiticeiro"),
    GOBLIN("Goblin"),
    ORC("Orc");

    private final String descricao;

    TipoInimigo(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
