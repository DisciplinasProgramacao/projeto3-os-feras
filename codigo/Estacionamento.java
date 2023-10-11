import java.time.LocalDateTime;

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
        for (Vaga vaga : vagas) {
            if (vaga.disponivel()) {
                // Encontrou uma vaga disponível, agora você precisa associar o veículo a essa
                // vaga.
                Veiculo veiculo = null;
                for (Cliente cliente : id) {
                    veiculo = cliente.possuiVeiculo(placa);
                    if (veiculo != null) {
                        break;
                    }
                }

                if (veiculo != null) {
                    boolean sucesso = veiculo.estacionar(vaga);
                    if (sucesso) {
                        System.out.println("Veículo com placa " + placa + " estacionado na vaga " + vaga.getId());
                    } else {
                        System.out.println("A vaga " + vaga.getId() + " já está ocupada.");
                    }
                    return;
                } else {
                    System.out.println("Veículo com placa " + placa + " não encontrado nos clientes.");
                    return;
                }
            }
        }
        System.out.println("Não há vagas disponíveis para estacionar o veículo com placa " + placa);
    }

    public double sair(String placa) {
        for (Cliente cliente : id) {
            Veiculo veiculo = cliente.possuiVeiculo(placa);
            if (veiculo != null) {
                UsoDeVaga[] usos = veiculo.getUsosDeVaga();
                for (UsoDeVaga uso : usos) {
                    if (uso.sair()) {
                        double valorPago = uso.valorPago();
                        System.out.println("Veículo com placa " + placa + " saiu da vaga " + uso.getVaga().getId() + " e o valor a ser pago é: R$" + valorPago);
                        return valorPago;
                    }
                }
            }
        }
        System.out.println("Veículo com placa " + placa + " não foi encontrado ou não está estacionado em nenhuma vaga.");
        return 0.0; 
    }
    

    public double totalArrecadado() {
        double totalArrecadado = 0.0;
        for (Vaga vaga : vagas) {
            UsoDeVaga uso = vaga.getUsoAtual();
            if (uso != null) {
                totalArrecadado += uso.valorPago();
            }
        }
        return totalArrecadado;
    }
    

    public double arrecadacaoNoMes(int mes) {
        double arrecadacaoNoMes = 0.0;
        for (Vaga vaga : vagas) {
            UsoDeVaga uso = vaga.getUsoAtual();
            if (uso != null) {
                LocalDateTime entrada = uso.getEntrada();
                if (entrada.getMonthValue() == mes) {
                    arrecadacaoNoMes += uso.valorPago();
                }
            }
        }
        return arrecadacaoNoMes;
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
                result.append((i + 1) + ". " + topClientes[i].getNome() + " - Valor arrecadado: "
                        + topClientes[i].arrecadadoNoMes(mes) + "\n");
            }
        }

        return result.toString();
    }

}
