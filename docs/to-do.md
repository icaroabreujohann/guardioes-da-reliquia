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
- [x] Validações: nome, cura negativa, personagem derrotado não age, habilidade em alvo derrotado
- [ ] Encerrar a defesa no fim da rodada
- [ ] Restaurar estado salvo (vida, recurso, defendendo, turnosAgidos, jaSeCurou)

## dominio.enums

- [x] `TipoAcao` (enum: ATACAR, DEFENDER, HABILIDADE, POCAO)
- [x] `ResultadoPartida` (enum: EM_ANDAMENTO, VITORIA, DERROTA)
- [x] `TipoGuardiao` (enum: ARQUEIRO, GUERREIRO, MAGO)
- [x] `TipoInimigo` (enum: FEITICEIRO, GOBLIN, ORC)

## dominio.utils

- [x] `FabricaPersonagens`
    - [x] `criarGuardiao(TipoGuardiao tipo, String nome)`
    - [x] `criarInimigoDoEncontro(int encontro)`
    - [x] `criarInimigoPorTipo(TipoInimigo tipo)`

## dominio.partida

- [ ] `Partida`
    - [ ] Atributos: jogador, inimigo, inventário, encontroAtual, turnoEncontro, turnoTotal, descansosUsados, resultado, aguardandoProximoEncontro, histórico
    - [ ] `executarRodada(TipoAcao acao)`
    - [ ] `usarPocaoEntreEncontros()`
    - [ ] `descansar()`
    - [ ] `avancarEncontro()`
    - [ ] `getPontuacao()`
    - [ ] `isEncerrada()`
- [x] `CalculadoraPontuacao`

## dominio.item

- [x] `Inventario`

## dominio.ranking

- [x] `RegistroRanking`
    - [x] Atributos: nome, classe, pontuação, resultado, turnos, data
    - [x] `vemAntes(RegistroRanking outro)`
- [x] `Ranking`
    - [x] `adicionar(RegistroRanking registro)`
    - [x] `getOrdenado()`

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