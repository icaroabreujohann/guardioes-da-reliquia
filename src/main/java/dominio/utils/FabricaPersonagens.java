package dominio.utils;

import dominio.enums.TipoGuardiao;
import dominio.enums.TipoInimigo;
import dominio.personagem.guardiao.Arqueiro;
import dominio.personagem.guardiao.Guardiao;
import dominio.personagem.guardiao.Guerreiro;
import dominio.personagem.guardiao.Mago;
import dominio.personagem.inimigo.Feiticeiro;
import dominio.personagem.inimigo.Goblin;
import dominio.personagem.inimigo.Inimigo;
import dominio.personagem.inimigo.Orc;

public class FabricaPersonagens {

    public static Guardiao criarGuardiao(TipoGuardiao tipo, String nome) {
        return switch (tipo) {
            case GUERREIRO -> new Guerreiro(nome);
            case ARQUEIRO -> new Arqueiro(nome);
            case MAGO -> new Mago(nome);
        };
    }

    public static Inimigo criarInimigoDoEncontro(int encontro) {
        return switch (encontro) {
            case 1 -> criarInimigoPorTipo(TipoInimigo.GOBLIN);
            case 2 -> criarInimigoPorTipo(TipoInimigo.ORC);
            case 3 -> criarInimigoPorTipo(TipoInimigo.FEITICEIRO);
            default -> throw new IllegalArgumentException("Encontro inválido: " + encontro);
        };
    }

    public static Inimigo criarInimigoPorTipo(TipoInimigo tipo) {
        return switch (tipo) {
            case GOBLIN -> new Goblin();
            case ORC -> new Orc();
            case FEITICEIRO -> new Feiticeiro();
        };
    }
}
