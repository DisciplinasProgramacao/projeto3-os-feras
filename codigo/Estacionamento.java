public class Estacionamento {

    private String nome;
    private Cliente[] id;
    private Vaga[] vagas;
    private int quantFileiras;
    private int vagasPorFileira;

    public Estacionamento(String nome, Cliente[] id, Vaga[] vagas, int quantFileiras, int vagasPorFileira) {
        this.nome = nome;
        this.id = id;
        this.vagas = vagas;
        this.quantFileiras = quantFileiras;
        this.vagasPorFileira = vagasPorFileira;
    }

    public void addVeiculo(Veiculo veiculo, String idCli) {
        Cliente clienteEncontrado = null;
        for (Cliente cliente : id) {
            if (cliente.getId().equals(idCli)) {
                clienteEncontrado = cliente;
                break;
            }
        }

        if (clienteEncontrado != null) {
            clienteEncontrado.addVeiculo(veiculo);
        } else {
            System.out.println("Cliente não encontrado com o ID especificado: " + idCli);
        }
    }

    public void addCliente(Cliente cliente) {
        if (id != null) {
            boolean clienteExistente = false;
            for (Cliente existingCliente : id) {
                if (existingCliente.getId().equals(cliente.getId())) {
                    clienteExistente = true;
                    break;
                }
            }

            if (!clienteExistente) {
                Cliente[] novoId = new Cliente[id.length + 1];
                System.arraycopy(id, 0, novoId, 0, id.length);
                novoId[novoId.length - 1] = cliente;
                id = novoId;
            } else {
                System.out.println("O cliente já existe na lista.");
            }
        } else {
            id = new Cliente[] { cliente };
        }
    }

    public void addVagas(Vaga[] novasVagas) {
    }

    private void gerarVagas() {
        int totalVagas = quantFileiras * vagasPorFileira;
        vagas = new Vaga[totalVagas];

        int vagaId = 1; 

        for (int fila = 1; fila <= quantFileiras; fila++) {
            for (int numero = 1; numero <= vagasPorFileira; numero++) {
                Vaga vaga = new Vaga(fila, numero);
                vagas[vagaId - 1] = vaga;
                vagaId++;
            }
        }
    }

    public void estacionar(String placa) {
    }

    public double sair(String placa) {
        return 0.0;
    }

    public double totalArrecadado() {
        return 0.0;
    }

    public double arrecadacaoNoMes(int mes) {
        return 0.0;
    }

    public double valorMedioPorUso() {
        if (id != null) {
            double totalValorPago = 0.0;
            int totalUsos = 0;
    
            for (Cliente cliente : id) {
                for (Veiculo veiculo : cliente.getVeiculos()) {
                    for (UsoDeVaga uso : veiculo.getUsosDeVaga()) {
                        totalValorPago += uso.valorPago();
                        totalUsos++;
                    }
                }
            }
    
            if (totalUsos > 0) {
                return totalValorPago / totalUsos;
            } else {
                System.out.println("Nenhum uso de vaga registrado.");
                return 0.0;
            }
        } else {
            System.out.println("Nenhum cliente cadastrado.");
            return 0.0;
        }
    }
    

    public String top5Clientes(int mes) {
        Cliente[] topClientes = new Cliente[5];
    
        for (int i = 0; i < 5; i++) {
            topClientes[i] = null;
        }
    
        for (Cliente cliente : id) {
            double arrecadacaoCliente = cliente.arrecadadoNoMes(mes);
    
            for (int i = 0; i < 5; i++) {
                if (topClientes[i] == null || arrecadacaoCliente > topClientes[i].arrecadadoNoMes(mes)) {
                    for (int j = 4; j > i; j--) {
                        topClientes[j] = topClientes[j - 1];
                    }
                    topClientes[i] = cliente;
                    break; 
                }
            }
        }
    
        StringBuilder result = new StringBuilder();
        result.append("Os cinco melhores clientes no mês " + mes + " são:\n");
        for (int i = 0; i < 5; i++) {
            if (topClientes[i] != null) {
                result.append((i + 1) + ". " + topClientes[i].getNome() + " - Valor arrecadado: " + topClientes[i].arrecadadoNoMes(mes) + "\n");
            }
        }
    
        return result.toString();
    }
    
}
