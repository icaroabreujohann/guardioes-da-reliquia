# Guardiões da Relíquia — To-do

Baseado no `docs/diagrama-classes.mmd`.

## dominio.personagem

- [x] `Personagem` (abstrata)
- [x] `Guardiao` (abstrata)
- [x] `Guerreiro`
- [x] `Arqueiro`
- [x] `Mago`
- [x] `Inimigo` (abstrata)
- [x] `Goblin`
- [x] `Orc`
- [x] `Feiticeiro`

## dominio.partida

- [ ] `Partida`
    - [ ] Atributos: jogador, inimigo, inventário, encontroAtual, turnoEncontro, turnoTotal, descansosUsados, resultado, aguardandoProximoEncontro, histórico
    - [ ] `executarRodada(TipoAcao acao)`
    - [ ] `usarPocaoEntreEncontros()`
    - [ ] `descansar()`
    - [ ] `avancarEncontro()`
    - [ ] `getPontuacao()`
    - [ ] `isEncerrada()`
- [ ] `TipoAcao` (enum: ATACAR, DEFENDER, HABILIDADE, POCAO)
- [x] `ResultadoPartida` (enum: EM_ANDAMENTO, VITORIA, DERROTA)
- [x] `CalculadoraPontuacao`
- [ ] `FabricaPersonagens`
    - [ ] `criarGuardiao(String classe, String nome)`
    - [ ] `criarInimigoDoEncontro(int encontro)`
    - [ ] `criarInimigoPorTipo(String tipo)`

## dominio.item

- [x] `Inventario`

## dominio.ranking

- [ ] `RegistroRanking`
    - [ ] Atributos: nome, classe, pontuação, resultado, turnos, data
    - [ ] `compareTo(RegistroRanking outro)`
- [ ] `Ranking`
    - [ ] `adicionar(RegistroRanking registro)`
    - [ ] `getOrdenado()`

## dominio.exceptions

- [x] `AcaoInvalidaException`

## persistencia

- [ ] `ConfiguracaoDados`
    - [ ] `definirPasta(Path pasta)`
    - [ ] `getArquivoSalvamento()`
    - [ ] `getArquivoRanking()`
- [ ] `RepositorioPartida`
    - [ ] `salvar(Partida partida)`
    - [ ] `carregar()`
- [ ] `RepositorioRanking`
    - [ ] `carregar()`
    - [ ] `registrar(RegistroRanking registro)`
- [ ] `PersistenciaException`

## ui

- [ ] `Main`
- [ ] `JanelaPrincipal`
- [ ] `TelaInicio`
- [ ] `TelaCombate`
- [ ] `TelaRanking`

## Testes, documentação e entrega

- [ ] Testes JUnit (10 casos do enunciado)
- [ ] Javadoc gerado em HTML
- [ ] `.jar` executável
- [ ] README
- [ ] Diagrama de classes atualizado