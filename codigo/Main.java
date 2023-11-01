import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Bem-vindo ao app de estacionamento!");
        System.out.println("Escolha uma das opções abaixo:");
        System.out.println("1 - Estacionamento");
        System.out.println("2 - Vaga");
        System.out.println("3 - Veiculo");
        System.out.println("4 - Histórico de Veiculo");
        System.out.println("5 - Sair");
        
        Estacionamento estacionamento = new Estacionamento(); // Criar um objeto de estacionamento

        int opcao = sc.nextInt();
        while (opcao != 5) {
            switch (opcao) {
                case 1:
                    TryQuest<Estacionamento> questEst = new TryQuest<Estacionamento>();
                    questEst.run();
                    break;
                case 2:
                    TryQuest<Vaga> questVaga = new TryQuest<Vaga>();
                    questVaga.run();
                    break;
                case 3:
                    TryQuest<Veiculo> questVeic = new TryQuest<Veiculo>();
                    questVeic.run();
                    break;
                case 4:
                    System.out.println("Digite a placa do veículo para ver o histórico:");
                    String placa = sc.next();
                    Veiculo veiculo = estacionamento.buscarVeiculo(placa);
                    if (veiculo != null) {
                        System.out.println("Histórico do Veículo " + placa);
                        for (UsoDeVaga uso : veiculo.getHistoricoVagas()) {
                            if (uso.getVaga() != null) {
                                System.out.println("Data de entrada: " + uso.getDataEntrada());
                                System.out.println("Data de saída: " + uso.getDataSaida());
                                System.out.println("Valor Pago: " + uso.getVaga().getValorPago());
                            }
                        }
                    } else {
                        System.out.println("Veículo não encontrado.");
                    }
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
            
            System.out.println("Escolha uma das opções abaixo:");
            System.out.println("1 - Estacionamento");
            System.out.println("2 - Vaga");
            System.out.println("3 - Veiculo");
            System.out.println("4 - Histórico de Veiculo");
            System.out.println("5 - Sair");
            opcao = sc.nextInt();
        }
        sc.close();
        System.out.println("Obrigado por usar o app de estacionamento!");
    }
}
