# Correção
  
  - Última atualização de 25/10 não está na master
  - **Veículo** não compila por erro de sintaxe (nome de classe)
  - **Veículo** não compila por uso de métodos inexistentes
  - **Veículo** não compila por exceção não tratada
  - **Vaga** não compila por erro de sintaxe (chave sobrando)
  - **Vaga** não compila por erro de sintaxe (declaração duplicada)
  - **Cliente** não compila por uso de métodos inexistentes
  - **Estacionamento** não compila por uso de métodos inexistentes
  - **UsoDeVaga** não compila por uso de métodos inexistentes
  - **UsoDeVaga** não calcula valor por tempo
  - **UsoDeVaga** não soma valor do serviço
  - **UsoDeVaga** enum dentro da classe
  - **TesteCliente** usa construtor inexistente
  - **App** tryquest ??? 
  - **App** não faz nada
  - Impossível testar/executar o app se nada compila corretamente
  - Sem documentação. O pouco que tem não é Javadoc

## Nota base do grupo: 2

  - Contribuições
    - Enrique
      - UsoDeVaga 2: ❌
      - Teste UsoDeVaga: ❌, construtor inexistente
      - Excecao Uso de vaga: ➕➖ (são várias)
    - Enzo 
      - Cliente: ❌, sem histórico
      - Verficicação do UML: ❌
      - Teste Vaga: ➕➖, um teste para cada requisito
    - Felipe
      - Estacionamento: ❌❌❌❌, sem documentação, 3 estruturas de cliente, enum sem documentação nem motivo
      - Teste Estacionamento: ❌ uso de construtor inexistente
      - Excecao: ➕➖ (são várias)
    - João Vitor 
      - Veiculo: ❌❌❌
      - App relatório:  ❌
      - Teste cliente: ❌❌
      - Serialização: ❌
    - Sthel 
      - UsoDeVaga: ❌❌
      - App ❌❌
      - Teste veículo: ✔️
    
- Tarefas nas aulas 04 e 11/10: 5 pontos;
    - Enrique ✔️✔️
    - Enzo ✔️❌
    - Felipe ➕➖ ✔️
    - João Vitor ➕➖❌
    - Sthel ➕➖ ✔️

- Requisitos : 2/12 pontos;
- Documentação: 0/3 pontos;


   

## Requisitos
  - Estacionar, sair e cobrança: R$4 a cada 15 minutos, com valor limite de R$50.  ❌
  - Serviços, tempo mínimo e cobrança ❌
  - Um cliente identificado tem acesso a seu histórico de uso do estacionamento.  ❌
  - Os dados das classes existentes devem ser salvos utilizando-se serialização; ❌
  - App:
    - Cadastrar estacionamentos com número de vagas
    - Veículos registrados por placa e ligados a clientes. 
    - Cliente identificado com nome e com mais de um veículo. 
    - Dados de clientes e veículos salvos e carregados.
    - 3 estacionamentos
	  - Gerar aleatoriamente 50 usos de vagas
  - Relatórios:
    - Valor total arrecadado do estacionamento;
    - Valor arrecadado em determinado mês;
    - Valor médio de cada utilização do estacionamento;
    - Ranking dos clientes que mais geraram arrecadação em um determinado mês.
  - Exceções que serão tratadas no aplicativo:
    - Sair de uma vaga cujo uso já foi finalizado;
    - Estacionar em uma vaga sem haver finalizado o uso anterior;
    - Cadastrar um cliente já existente;
    - Cadastrar um veículo já existente;
  






