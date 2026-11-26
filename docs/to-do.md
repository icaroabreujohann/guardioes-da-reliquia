# Guardiões da Relíquia — To-do

Baseado no `docs/diagrama-classes.mmd`.

## dominio

- [x] `AcaoInvalidaException`

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
- [x] Restaurar estado salvo (vida, recurso, defendendo, turnosAgidos, jaSeCurou)
- [x] `FabricaPersonagens`
    - [x] `criarGuardiao(TipoGuardiao tipo, String nome)`
    - [x] `criarInimigoDoEncontro(int encontro)`
    - [x] `criarInimigoPorTipo(TipoInimigo tipo)`

## dominio.personagem.guardiao

- [x] `TipoGuardiao` (enum: ARQUEIRO, GUERREIRO, MAGO)

## dominio.personagem.inimigo

- [x] `TipoInimigo` (enum: FEITICEIRO, GOBLIN, ORC)

## dominio.partida

- [x] `Partida`
    - [x] Atributos: jogador, inimigo, inventário, encontroAtual, turnoEncontro, turnoTotal, descansosUsados, resultado, aguardandoProximoEncontro, histórico
    - [x] `executarRodada(TipoAcao acao)`
    - [x] `usarPocaoEntreEncontros()`
    - [x] `descansar()`
    - [x] `avancarEncontro()`
    - [x] `getPontuacao()`
    - [x] `isEncerrada()`
    - [x] Construtor de restauração (confere inimigo do encontro, contadores, fase e poções)
- [x] `CalculadoraPontuacao`
- [x] `TipoAcao` (enum: ATACAR, DEFENDER, HABILIDADE, POCAO)
- [x] `ResultadoPartida` (enum: EM_ANDAMENTO, VITORIA, DERROTA)

## dominio.item

- [x] `Inventario`

## dominio.ranking

- [x] `RegistroRanking`
    - [x] Atributos: nome, classe, pontuação, resultado, turnos, data
    - [x] `vemAntes(RegistroRanking outro)`
- [x] `Ranking`
    - [x] `adicionar(RegistroRanking registro)`
    - [x] `getOrdenado()`

## persistencia

- [x] `ConfiguracaoDados`
    - [x] `definirPasta(Path pasta)`
    - [x] `getArquivoSalvamento()`
    - [x] `getArquivoRanking()`
- [x] `RepositorioPartida`
    - [x] `salvar(Partida partida)`
    - [x] `carregar()`
    - [x] `existeSalvamento()`
    - [x] `apagarSalvamento()`
- [x] `RepositorioRanking`
    - [x] `carregar()`
    - [x] `registrar(RegistroRanking registro)`
- [x] `PersistenciaException`
- [x] `ArquivoTexto` (leitura UTF-8 e gravação via `.tmp`, usada pelos dois repositórios)

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