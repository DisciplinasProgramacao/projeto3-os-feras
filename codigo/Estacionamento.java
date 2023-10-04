public class Estacionamento {

    private String nome;
    private Cliente[] id;
    private Vaga[] vagas;
    private int quantFileiras;
    private int vagasPorFileira;

    public Estacionamento(String nome, int capacidadeMaximaClientes, Vaga[] vagas, int quantFileiras, int vagasPorFileira) {
        this.nome = nome;
        this.id = new Cliente[capacidadeMaximaClientes];
        this.vagas = vagas;
        this.quantFileiras = quantFileiras;
        this.vagasPorFileira = vagasPorFileira;
    }

    public void addVeiculo(Veiculo veiculo, String idCli) {
    }

    public void addCliente(Cliente cliente) {
        for (int i = 0; i < id.length; i++) {
            if (id[i] == null) {
                id[i] = cliente;
                return; 
            }
        }
        System.out.println("A capacidade máxima de clientes foi atingida.");
    }

    public void addVagas(Vaga[] novasVagas) {
        this.vagas = novasVagas;
    }

    private void gerarVagas() {
    }

    public void estacionar(String placa) {
    }

    public double sair(String placa) {
    }

    public double totalArrecadado() {
    }

    public double arrecadacaoNoMes(int mes) {
    }

    public double valorMedioPorUso() {
    }

    public String top5Clientes(int mes) {
    }
}
