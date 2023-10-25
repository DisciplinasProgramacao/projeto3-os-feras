import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in); // Criar um objeto Scanner para ler a entrada do usuário
		System.out.println("Bem-vindo ao app de estacionamento!"); // Imprimir uma mensagem de boas-vindas
		System.out.println("Escolha uma das opções abaixo:"); // Imprimir as opções disponíveis
		System.out.println("1 - Estacionamento");
		System.out.println("2 - Vaga");
		System.out.println("3 - Veiculo");
		System.out.println("4 - Sair");
		int opcao = sc.nextInt(); // Ler a opção escolhida pelo usuário
		while (opcao != 4) { // Enquanto a opção não for 4 (sair)
			switch (opcao) { // Usar um switch para executar a ação correspondente à opção
				case 1: // Se a opção for 1 (Estacionamento)
					TryQuest<Estacionamento> questEst = new TryQuest<Estacionamento>(); // Criar um objeto TryQuest para a classe Estacionamento
					questEst.run(); // Executar o método run do objeto TryQuest
					break;
				case 2: // Se a opção for 2 (Vaga)
					TryQuest<Vaga> questVaga = new TryQuest<Vaga>(); // Criar um objeto TryQuest para a classe Vaga
					questVaga.run(); // Executar o método run do objeto TryQuest
					break;
				case 3: // Se a opção for 3 (Veiculo)
					TryQuest<Veiculo> questVeic = new TryQuest<Veiculo>(); // Criar um objeto TryQuest para a classe Veiculo
					questVeic.run(); // Executar o método run do objeto TryQuest
					break;
				default: // Se a opção for inválida
					System.out.println("Opção inválida. Tente novamente."); // Imprimir uma mensagem de erro
			}
			System.out.println("Escolha uma das opções abaixo:"); // Imprimir novamente as opções disponíveis
			System.out.println("1 - Estacionamento");
			System.out.println("2 - Vaga");
			System.out.println("3 - Veiculo");
			System.out.println("4 - Sair");
			opcao = sc.nextInt(); // Ler novamente a opção escolhida pelo usuário
		}
		sc.close(); // Fechar o objeto Scanner
		System.out.println("Obrigado por usar o app de estacionamento!"); // Imprimir uma mensagem de despedida
	}

}
