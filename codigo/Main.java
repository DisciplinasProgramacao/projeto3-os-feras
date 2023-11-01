import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Estacionamento estacionamento = new Estacionamento("Estacionamento ABC", 5, 10);

        System.out.println("Bem-vindo ao " + estacionamento.getNome() + "!");
        int escolha;

        do {
            System.out.println("Escolha a ação desejada:");
            System.out.println("1 - Entrar no estacionamento");
            System.out.println("2 - Sair do estacionamento");
            System.out.println("3 - Consultar vagas disponíveis");
            System.out.println("4 - Consultar valor da hora");
            System.out.println("5 - Sair do app");

            escolha = scanner.nextInt();
            scanner.nextLine(); // Limpar a quebra de linha após a leitura do número

            switch (escolha) {
                case 1:
                    System.out.println("Digite o nome do cliente:");
                    String nomeCliente = scanner.nextLine();
                    System.out.println("Digite a placa do veículo:");
                    String placaVeiculo = scanner.nextLine();
                    System.out.println("Digite o tipo do veículo:");
                    String tipoVeiculo = scanner.nextLine();

                    Veiculo veiculo = new Veiculo(placaVeiculo, tipoVeiculo);
                    Cliente cliente = new Cliente(nomeCliente, veiculo);

                    if (estacionamento.estacionar(cliente)) {
                        System.out.println("Cliente estacionado com sucesso!");
                    } else {
                        System.out.println("Estacionamento lotado. Não é possível estacionar.");
                    }
                    break;

                case 2:
                    System.out.println("Digite o nome do cliente:");
                    nomeCliente = scanner.nextLine();
                    Cliente clienteSaida = estacionamento.buscarCliente(nomeCliente);

                    if (clienteSaida != null) {
                        double valor = estacionamento.sair(clienteSaida);
                        System.out.println("Cliente " + nomeCliente + " saiu do estacionamento com placa " +
                                clienteSaida.getVeiculo().getPlaca() + ". O valor a ser pago é R$ " + valor + ".");
                    } else {
                        System.out.println("O cliente " + nomeCliente + " não está no estacionamento.");
                    }
                    break;

                case 3:
                    int vagasDisponiveis = estacionamento.getVagasDisponiveis();
                    System.out.println("O estacionamento tem " + vagasDisponiveis + " vagas disponíveis.");
                    break;

                case 4:
                    System.out.println("Digite o id da vaga que você deseja consultar, ou deixe em branco para " +
                            "consultar o valor da hora do estacionamento:");
                    String idVaga = scanner.nextLine();

                    if (idVaga.isEmpty()) {
                        double valorHora = estacionamento.getValorHora();
                        System.out.println("O valor da hora do estacionamento é R$ " + valorHora + ".");
                    } else {
                        Vaga vaga = estacionamento.buscarVaga(idVaga);

                        if (vaga != null) {
                            double valorHora = vaga.getValorHora();
                            System.out.println("O valor da hora da vaga " + idVaga + " é R$ " + valorHora + ".");
                        } else {
                            System.out.println("A vaga " + idVaga + " não existe no estacionamento.");
                        }
                    }
                    break;

                case 5:
                    System.out.println("Obrigado por usar o app de estacionamento!");
                    break;

                default:
                    System.out.println("Escolha inválida. Tente novamente.");
                    break;
            }
        } while (escolha != 5);

        scanner.close();
    }
}
