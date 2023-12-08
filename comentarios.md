# Correção P4 (commit de 25/11)

  - **GRAVE** 25/11 sem um diagrama disponível dentro das especificações.
  - **Cliente** usando vários métodos que não existem. Como não tem diagrama para seguir... 
  - **Cliente** enum tem turno, mas não tem mensalista. Duas variáveis static para as regras ferem mortalmente a modularidade e o polimorfismo.
  - **Servico** tem o preço, mas não tem o tempo
  - **Estacionamento** continua com um scanner nos atributos
  - **Estacionamento** tem uma lista **e** um mapa de clientes?
  - **Estacionamento** tem print no método mostrar vagas
  - **Estacionamento** com método com assinatura errada, derrubando o app
  - **Estacionamento** deixando de gerar/tratar exceções para entrada e saida
  - **Main** chamando métodos construtores que não existem e prejudicado por método do estacionamento
  - **Main** deixando de fazer tratamento apropriado de exceção (linha 87, por exemplo)
  - **Main** chamando método que não existe (getVagasDisponíveis - que requisito é este?)
  - **Main** chamando get em uso de Vaga... 
  - **Veículo** retornando lista em lugar de relatório (string)

  - Nenhuma implementação dos planos de uso do cliente. 
  - Documentação incompleta ou faltante, mais de 2 meses depois do início do projeto. 

  Pessoal, infelizmente vocês têm demonstrado ao longo do tempo muitas deficiências no entendimento do conceito de modularidade. Em diversos momentos, optam por desenvolver usando os conceitos básicos de AED-I, que são importantes, mas precisam ser evoluídos aqui. Por exemplo, modularidade, abstração e polimorfismo: se o cliente pode ter 3 regras, não pode ficar tudo concentrado numa única classe. Isso já vinha acontecendo com o enum de serviços, com o uso de vaga... Modularidade e OO não é simplesmente criar diversas classes e crer que o problema está resolvido. O aprendizado de programação requer estudo, organização e comprometimento. Infelizmente, eu vejo o sistema de vocês como muito comprometido a estas alturas do semestre. 
  
## Nota base do grupo 6

  - Requisitos corretamente implementados (classes e testes): 4/12 pontos;
  - Documentação de código: 1/3 pontos;
  - Tarefas nas aulas ao longo do projeto: 1/5 pontos (de 06 a 20/11 sem commits)
  

## Colaborações
  - Enrique 
    - Turno ➕➖
    - Serviço ➕➖
  - Enzo
    - Cliente ⚠️
  - Felipe
    - Estacionamento ⚠️
  - João V. 
    - Veículo ⚠️ (sem documentacao)
  - Sthel
    - Main ⚠️
  
	

## Requisitos
  - Diagrama atualizado ❌
  - Estruturas de armazenamento eficientes;  ✔️
  - Aplicativo com acesso aos requisitos principais e aos relatórios gerenciais;  ➕➖
  - Aplicativo com base de teste cadastrada; ❌
  - Aplicativo com funcionamento robusto; ➕➖
  - Cliente categorizado em três modalidades: horista, de turno ou mensalista. ⚠️
  - Regra de cliente horista; ❌
  - Regra de cliente mensalista; ❌
  - Regra de cliente de turno; ❌
  - Relatórios:
     - A arrecadação total de cada um dos estacionamentos, em ordem decrescente; ❌
    
❌
➕➖
✔️
⚠️