package dominio.ranking;

import java.util.ArrayList;
import java.util.List;

public class Ranking {
    private final List<RegistroRanking> registros = new ArrayList<>();

    public Ranking() {
    }

    public void adicionar(RegistroRanking registro) {
        if (registro == null)
            throw new IllegalArgumentException("O registro não pode ser nulo.");

        registros.add(registro);
    }

    public List<RegistroRanking> getOrdenado() {
        List<RegistroRanking> ordenado = new ArrayList<>(registros); // ordena uma cópia para quem recebe não alterar o ranking

        for (int i = 0; i < ordenado.size() - 1; i++) {
            for (int j = 0; j < ordenado.size() - 1 - i; j++) { // os últimos i já estão no lugar certo
                if (ordenado.get(j + 1).vemAntes(ordenado.get(j))) { // o de baixo deveria vir antes, então troca
                    RegistroRanking temp = ordenado.get(j);
                    ordenado.set(j, ordenado.get(j + 1));
                    ordenado.set(j + 1, temp);
                }
            }
        }
        return ordenado;
    }
}
