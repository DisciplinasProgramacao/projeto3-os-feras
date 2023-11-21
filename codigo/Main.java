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
            System.out.println("5 - Consultar relatório de uso por veículo");
            System.out.println("6 - Sair do app");

            escolha = scanner.nextInt();
            scanner.nextLine(); // Limpar a quebra de linha após a leitura do número

            switch (escolha) {
                case 1:
                    entradaEstacionamento(scanner, estacionamento);
                    break;

                case 2:
                    saidaEstacionamento(scanner, estacionamento);
                    break;

                case 3:
                    consultarVagasDisponiveis(estacionamento);
                    break;

                case 4:
                    consultarValorHora(scanner, estacionamento);
                    break;

                case 5:
                    consultarRelatorioPorVeiculo(scanner, estacionamento);
                    break;

                case 6:
                    System.out.println("Obrigado por usar o app de estacionamento!");
                    break;

                default:
                    System.out.println("Escolha inválida. Tente novamente.");
                    break;
            }
        } while (escolha != 6);

        scanner.close();
    }

    private static void entradaEstacionamento(Scanner scanner, Estacionamento estacionamento) {
        System.out.println("Digite o nome do cliente:");
        String nomeCliente = scanner.nextLine();
        System.out.println("Digite a placa do veículo:");
        String placaVeiculo = scanner.nextLine();
        System.out.println("Digite o tipo do veículo:");
        String tipoVeiculo = scanner.nextLine();

        try {
            Veiculo veiculo = new Veiculo(placaVeiculo, tipoVeiculo);
            Cliente cliente = new Cliente(nomeCliente, veiculo);

            if (estacionamento.estacionar(cliente)) {
                System.out.println("Cliente estacionado com sucesso!");
            } else {
                System.out.println("Estacionamento lotado. Não é possível estacionar.");
            }
        } catch (ExcecaoVeiculoJaCadastrado e) {
            System.out.println(e.getMessage());
        }
    }

    private static void saidaEstacionamento(Scanner scanner, Estacionamento estacionamento) {
        System.out.println("Digite a placa do veículo:");
        String placaVeiculo = scanner.nextLine();
        try {
            double valorPago = estacionamento.sair(placaVeiculo);
            System.out.println("O veículo com a placa " + placaVeiculo + " saiu do estacionamento. Valor pago: R$" + valorPago);
        } catch (UsoDeVagaException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void consultarVagasDisponiveis(Estacionamento estacionamento) {
        int vagasDisponiveis = estacionamento.getVagasDisponiveis();
        System.out.println("O estacionamento tem " + vagasDisponiveis + " vagas disponíveis.");
    }

    private static void consultarValorHora(Scanner scanner, Estacionamento estacionamento) {
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
    }

    private static void consultarRelatorioPorVeiculo(Scanner scanner, Estacionamento estacionamento) {
        System.out.println("Digite a placa do veículo para consultar o relatório:");
        String placaVeiculo = scanner.nextLine();

        Veiculo veiculo = estacionamento.buscarVeiculo(placaVeiculo);

        if (veiculo != null) {
            System.out.println("Relatório de uso para o veículo com placa " + placaVeiculo + ":");
            List<UsoDeVaga> relatorio = veiculo.gerarRelatorio(true);

            for (UsoDeVaga uso : relatorio) {
                System.out.println("Data de entrada: " + uso.getDataEntrada() + ", Valor pago: R$" + uso.getValorPago());
            }
        } else {
            System.out.println("Veículo com placa " + placaVeiculo + " não encontrado no estacionamento.");
        }
    }
}
