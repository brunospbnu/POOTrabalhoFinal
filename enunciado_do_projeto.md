# 1 Objetivo e organização

**Equipe:** dupla
**Tema:** jogo Guardiões da Relíquia, uma aventura tática por turnos para um jogador contra o computador.

**Objetivos:**
* abordar conceitos de orientação a objetos trabalhados na disciplina;
* implementar interface gráfica utilizando componentes Swing (telas Java - não será considerado somente utilização de JOptionPane ou diálogos);
* persistência em arquivos; 
* testes automatizados;
* documentação.

**IMPORTANTE:**
Os dois integrantes devem participar do desenvolvimento e dominar todo o projeto. A divisão de tarefas não limita os conteúdos que poderão ser cobrados individualmente.

# 2 Situação do jogo

Um guardião deve vencer três encontros consecutivos para recuperar uma relíquia.
Ao iniciar uma partida, o jogador informa seu nome e escolhe uma entre três classes de personagem: Guerreiro, Arqueiro ou Mago.
Cada classe possui atributos e uma habilidade especial próprios. Os adversários também devem apresentar comportamentos distintos.
A vitória ocorre ao derrotar o adversário do terceiro encontro.
A derrota ocorre quando a vida do jogador chega a zero.
A partida encerrada deve mostrar o resultado, a quantidade de turnos e a pontuação.
Definam e documentem uma fórmula de pontuação que considere vitória e desempenho, sem produzir pontuação negativa.

# 3 Regras mínimas de combate

Cada rodada contém uma ação válida do jogador e, se o inimigo continuar vivo, uma ação automática do inimigo. O inimigo decide e age em sequência, dentro do mesmo fluxo do turno. Uma ação inválida não altera o estado da partida nem consome o turno.
O jogador pode atacar, defender, usar habilidade especial ou consumir uma poção do inventário. A defesa reduz apenas o próximo dano recebido e expira ao recebê-lo. A habilidade consome um recurso limitado, como energia ou mana; sua utilização deve ser recusada quando o recurso for insuficiente.

# 4 Escopo funcional obrigatório

* **Dano e vida:** adotem uma regra explícita de cálculo de dano, incluindo defesa e habilidades. A vida deve permanecer entre zero e o máximo do personagem. Cura não pode ultrapassar esse máximo; um personagem derrotado não pode agir. Documentem os valores iniciais e as fórmulas para que os resultados sejam verificáveis.
* **Progressão:** implementem três encontros com dificuldade crescente e pelo menos três tipos de inimigo com decisões diferentes. Entre encontros, mantenham o estado do jogador e permitam administrar o inventário antes de prosseguir. O jogador começa com pelo menos duas poções; cada uso consome uma unidade e deve ser recusado quando o estoque estiver vazio.
* **Estado da partida:** controlem encontro atual, turno, personagem, inimigo, inventário e resultado. Depois de vitória ou derrota, bloqueiem novas ações de combate. Disponibilizem nova partida, salvar, carregar e consultar ranking. O ranking deve registrar pelo menos nome, classe, pontuação e resultado, ordenado por pontuação decrescente, com regra de desempate documentada.

A dupla pode ampliar o tema e os elementos visuais, desde que preserve todos os requisitos. Não são exigidos animações, som, rede, banco de dados, inteligência artificial avançada nem movimentação em tempo real.

# 5 Modelagem orientada a objetos

Utilizem encapsulamento, atributos com acesso controlado, métodos coesos e construtores que criem objetos válidos.
As regras de domínio devem funcionar sem depender de componentes Swing, permitindo testes automatizados.
Implementem pelo menos uma classe abstrata de personagem, com atributos e comportamentos comuns, e subclasses concretas com especializações relevantes. A herança deve representar uma relação coerente entre os tipos. Utilizem abstração para definir operações que as subclasses devem implementar.
Demonstrem polimorfismo real: uma referência do tipo abstrato deve executar comportamentos sobrescritos de diferentes subclasses, por exemplo ao aplicar uma habilidade. Não substituam esse mecanismo por uma sequência de verificações do nome ou do tipo de cada classe.
Empreguem composição para representar partida e inventário. Separem responsabilidades em classes e pacotes de domínio, interface e persistência.
Entreguem um diagrama de classes simplificado, com herança, associações e principais atributos e métodos.

# 6 Exceções e consistência

Prevejam exceções para dados inválidos e operações impossíveis. Criem pelo menos uma exceção própria de domínio, com uso justificado, e tratem as exceções de entrada e saída de arquivos. Informem o problema ao usuário de forma clara, sem encerrar abruptamente a aplicação.
Não ocultem erros com blocos de captura vazios. Validem uma operação antes de alterar o estado. Ao falhar um carregamento, mantenham a partida atual íntegra; um arquivo inválido não deve substituir parcialmente os objetos em uso.

# 7 Interface gráfica e execução por turnos

Toda a interação do usuário deve ocorrer em Java Swing. A tela deve apresentar nome e classe do jogador, vida e recurso disponíveis, inimigo e sua vida, encontro e turno atuais, inventário, histórico das ações e botões para as ações permitidas. Incluam identificação clara de vitória e derrota e mensagens para entradas inválidas.
Habilitem ou desabilitem comandos conforme o estado da partida. O histórico deve mostrar a ação realizada e seus efeitos. Salvamento e carregamento devem ocorrer entre turnos completos, nunca no meio da resolução de uma ação.
Não utilizem corrotinas, threads criadas pela dupla, executores, tarefas paralelas, SwingWorker ou temporizadores para conduzir o jogo. Não implementem laços que aguardem uma ação do usuário nem pausas com Thread.sleep. O avanço deve decorrer dos eventos dos botões, resolvendo as ações sequencialmente.

# 8 Arquivos no Windows

Persistam localmente os dados em arquivos do sistema de arquivos do Windows, sem banco de dados ou serviço externo. Este tema ainda será visto em sala de aula.
A pasta de dados deve ser configurável pelo usuário do jogo. A aplicação deve lidar com caminhos que contenham espaços e caracteres acentuados.
O salvamento deve preservar tudo o que é necessário para continuar a partida no mesmo ponto: classe e atributos, vida, recurso, encontro, turno, inimigo atual, inventário, pontuação e demais estados relevantes. Carregar não pode restaurar recursos indevidamente nem alterar o progresso. O ranking deve permanecer disponível após fechar e reabrir o jogo.
Escolham e documentem um formato de arquivo, com identificação de versão. Tratem arquivo inexistente, formato inválido, dados inconsistentes e falha de leitura ou gravação. Expliquem como evitar perda de um salvamento válido quando uma gravação falhar, por exemplo gravando primeiro em arquivo temporário e substituindo o destino somente após sucesso.

# 9 Documentação e distribuição

Produzam Javadoc das classes, construtores e métodos públicos desenvolvidos, descrevendo responsabilidades, parâmetros, retornos, exceções e regras relevantes. Gerem a documentação HTML e incluam seu ponto de entrada index.html. Não basta entregar comentários sem gerar a documentação.
Gerem um arquivo .jar executável com classe principal configurada. O jogo deve iniciar no Windows pelo comando java -jar NomeDoJogo.jar, fora da IDE, com os recursos e dependências de execução necessários. 

# 10 Testes automatizados com JUnit

Implementem no mínimo 10 casos de teste distintos e necessários em JUnit, com pelo menos 5 positivos e 5 negativos. Cada caso deve ter preparação, execução e asserções relevantes. Casos repetidos com nomes diferentes não atendem ao mínimo. Os testes devem executar automaticamente e sem interação com a interface gráfica.
A matriz abaixo define a cobertura mínima. Adaptem os nomes às classes e às regras implementadas, mantendo os cenários. Testes de arquivos devem usar diretórios temporários e não podem apagar ou modificar os dados reais do usuário.

| Caso | Tipo | Comportamento a verificar |
| :--- | :--- | :--- |
| 1 | Positivo | Construção válida inicializa atributos e inventário corretamente. |
| 2 | Positivo | Ataque calcula o dano e atualiza a vida conforme a regra. |
| 3 | Positivo | Defesa reduz o próximo dano e depois expira. |
| 4 | Positivo | Habilidade via referência abstrata aplica o comportamento da subclasse e consome recurso. |
| 5 | Positivo | Salvar e carregar preserva o estado completo de uma partida em andamento. |
| 6 | Negativo | Construtor rejeita atributo obrigatório inválido. |
| 7 | Negativo | Habilidade sem recurso é recusada sem mudar o estado nem consumir turno. |
| 8 | Negativo | Uso de poção com estoque vazio é recusado sem efeitos. |
| 9 | Negativo | Arquivo corrompido ou inconsistente é recusado e preserva a partida atual. |
| 10 | Negativo | Ação após vitória ou derrota é bloqueada e mantém o estado final. |

Incluam testes adicionais para os limites que julgarem necessários, como cura máxima, vida zero, transição de encontro e ranking. Quando houver aleatoriedade, controlem a fonte de valores nos testes para obter resultados reproduzíveis.

# 11 Material obrigatório para a apresentação

Organizem uma pasta única com código-fonte completo, testes JUnit, configuração de compilação e dependências, .jar executável, Javadoc HTML, diagrama de classes e manual README (se necessário). Incluam um salvamento de exemplo e instruções para executar, testar, gerar o Javadoc e gerar novamente o .jar.
O README deve explicar as regras e fórmulas, estrutura dos arquivos, versão do Java, controles da interface e decisões de modelagem. Arquivos e demonstração são evidências para a arguição e não recebem nota automática. A dupla deve levar o material pronto para execução em sala, inclusive sem depender de acesso à internet.

# 12 Prazo e apresentação

O trabalho deve estar concluído e ser apresentado até o último dia de aula da disciplina. Assim que a dupla estiver pronta, deverá solicitar à professora o agendamento. A apresentação será oral, em sala de aula, somente para a professora, com a presença dos dois integrantes. Não haverá segunda chance de apresentação ou de arguição.
A entrega e a avaliação ocorrerão por meio dessa apresentação. A dupla demonstrará o jogo e disponibilizará os materiais obrigatórios à professora. Cada integrante responderá individualmente às perguntas sobre qualquer parte do projeto, sem que o colega responda em seu lugar. Ambos devem saber explicar, localizar e justificar o código, os testes e as decisões adotadas.

# 13 Avaliação exclusivamente pela arguição oral

A avaliação será realizada exclusivamente por meio de apresentação e arguição oral em sala de aula, com duração máxima de 20 minutos por dupla, incluindo a demonstração do projeto e as perguntas individuais. Quando estiver pronta, a dupla deverá solicitar à professora o agendamento, respeitando o prazo do último dia de aula. Não haverá segunda oportunidade de apresentação ou arguição.
Os dois integrantes deverão participar e demonstrar domínio de todo o trabalho, independentemente da divisão de tarefas adotada durante o desenvolvimento. A professora poderá direcionar perguntas diferentes a cada estudante e solicitar explicações sobre o código, as decisões de implementação e o funcionamento do jogo.

**Conteúdos que poderão ser abordados**

A arguição poderá contemplar:
* Modelagem orientada a objetos: classes, objetos, encapsulamento, construtores, abstração, herança, polimorfismo, composição e distribuição de responsabilidades.
* Regras do jogo: controle dos turnos, ações dos personagens, cálculos, inventário, progressão e condições de vitória e derrota.
* Interface Swing: componentes utilizados, tratamento de eventos, atualização das informações e separação entre interface e regras de negócio.
* Arquivos no Windows: organização dos dados, caminhos, salvamento, carregamento e preservação do estado da partida.
* Validações e exceções: prevenção de operações inválidas, tratamento de erros e manutenção da consistência dos dados.
* Testes JUnit: escolha dos cenários, testes positivos e negativos, asserções e interpretação dos resultados.
* Javadoc e distribuição: documentação das classes e métodos, geração da documentação e construção e execução do arquivo .jar.

A professora também poderá solicitar que o estudante localize uma implementação, explique um trecho de código, antecipe o resultado de uma execução, identifique um problema ou descreva como resolver determinada situação.

**Critérios de atribuição da nota**

A nota será individual, considerando a correção das respostas, a clareza das explicações, a capacidade de relacionar os conceitos ao próprio projeto e a autonomia demonstrada durante a arguição.
A quantidade, a profundidade e a seleção das perguntas serão definidas pela professora conforme o tempo disponível e o acompanhamento do estudante ao longo da disciplina, mantendo critérios de exigência equivalentes. Esse acompanhamento orientará a condução e a contextualização das perguntas, sem constituir pontuação separada.
A arguição oral corresponde a 100% da nota do trabalho. Os conteúdos técnicos serão avaliados de forma integrada, sem pesos fixos por componente ou quantidade obrigatória de perguntas. Poderão ser reconhecidas respostas parcialmente corretas, conforme a compreensão demonstrada.
A nota expressará o percentual de domínio demonstrado individualmente na arguição. Assim, um estudante que demonstrar aproximadamente 50% do domínio esperado receberá 50% da nota máxima, mesmo que a implementação esteja totalmente correta.
A entrega, a documentação, os testes e o funcionamento do programa são requisitos obrigatórios e servem de evidência para as perguntas, mas não geram pontuação automática nem substituem a demonstração individual de aprendizagem. Os integrantes da mesma dupla poderão receber notas diferentes.