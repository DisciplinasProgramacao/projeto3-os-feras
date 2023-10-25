import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Estacionamento {

    private String nome;
    private List<Cliente> cliente = new ArrayList<>();
    private List<Vaga> vagas = new ArrayList<>();
    private int quantFileiras;
    private int vagasPorFileira;
    private Cliente[] id;
    private Cliente[] clientes;

    public Estacionamento(String nome, Cliente[] id, List<Vaga> vagas, int quantFileiras, int vagasPorFileira) {
        this.nome = nome;
        this.id = id;
        this.vagas = vagas;
        this.quantFileiras = quantFileiras;
        this.vagasPorFileira = vagasPorFileira;
    }

    public String addVeiculo(Veiculo veiculo, Object object) {
        Cliente clienteEncontrado = null;
        for (Cliente cliente : id) {
            if (cliente.getId().equals(object)) {
                clienteEncontrado = cliente;
                break;
            }
        }
    
        if (clienteEncontrado != null) {
            clienteEncontrado.addVeiculo(veiculo);
            return "Veículo adicionado com sucesso.";
        } else {
            throw new EstacionamentoException("Veículo não está associado a nenhum cliente.");
        }
    }
    
    
    
    



    public void addCliente(String nome, Object object) {
        if (clienteJaExiste(object)) {
            throw new EstacionamentoException("Cliente já existe.");
        } else {
            cliente.add(new Cliente(nome, (String) object));
        }
    }

    public boolean clienteJaExiste(Object object) {
        for (Cliente cliente : cliente) {
            if (cliente.getId().equals(object)) {
                return true;
            }
        }
        return false;
    }
    








    public void addVagas(String id, boolean disponivel) {
        if (vagaJaExiste(id)) {
            throw new EstacionamentoException("Vaga já existe.");
        } else {
            vagas.add(new Vaga(id, disponivel));
        }
    }
    
    private boolean vagaJaExiste(String id) {
        for (Vaga v : vagas) {
            if (v.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }
    
    
    

    private void gerarVagas() {
        if (!vagas.isEmpty()) {
            throw new EstacionamentoException("As vagas já foram geradas.");
        }
    
        int totalVagas = quantFileiras * vagasPorFileira;
        vagas = new ArrayList<>();
    
        for (int fila = 1; fila <= quantFileiras; fila++) {
            for (int numero = 1; numero <= vagasPorFileira; numero++) {
                Vaga vaga = new Vaga(fila, numero);
                vagas.add(vaga);
            }
        }
    }
    

    public enum EstacionamentoStatus {
        SUCCESS,            // Estacionamento bem-sucedido
        VAGA_OCUPADA,       // A vaga está ocupada
        VEICULO_NAO_ENCONTRADO,  // Veículo não encontrado
        SEM_VAGAS_DISPONIVEIS    // Sem vagas disponíveis
    }


    public Estacionamento estacionar(Object object) {
        for (Vaga vaga : vagas) {
            if (vaga.disponivel()) {
                Veiculo veiculo = null;
                for (Cliente cliente : id) {
                    veiculo = cliente.possuiVeiculo(object);
                    if (veiculo != null) {
                        break;
                    }
                }
    
                if (veiculo != null) {
                    boolean sucesso = veiculo.estacionar(vaga);
                    if (sucesso) {
                        return this; // Retorna a instância atual (Estacionamento) para indicar sucesso.
                    } else {
                        throw new EstacionamentoException("A vaga está ocupada.");
                    }
                } else {
                    throw new EstacionamentoException("Veículo não encontrado.");
                }
            }
        }
        throw new EstacionamentoException("Sem vagas disponíveis.");
    }
    
    

    public double sair(Object object) {
        for (Cliente cliente : id) {
            Veiculo veiculo = cliente.possuiVeiculo(object);
            if (veiculo != null) {
                UsoDeVaga[] usos = veiculo.getUsosDeVaga();
                double valorTotalPago = 0.0;
                boolean encontrouUso = false;
    
                for (UsoDeVaga uso : usos) {
                    if (uso.sair()) {
                        encontrouUso = true;
                        valorTotalPago += uso.valorPago();
                    }
                }
    
                if (encontrouUso) {
                    return valorTotalPago;
                }
            }
        }
    
        throw new EstacionamentoException("Veículo não encontrado ou não possui usos de vaga registrados.");
    }
    

    public double totalArrecadado() {
        double totalArrecadado = 0.0;
        for (Vaga vaga : vagas) {
            UsoDeVaga uso = vaga.getUsoAtual();
            if (uso != null) {
                totalArrecadado += uso.valorPago();
            }
        }
    
        if (totalArrecadado >= 0.0) {
            return totalArrecadado;
        } else {
            throw new EstacionamentoException("Nenhum uso de vaga registrado ou valor inválido.");
        }
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
    
        if (arrecadacaoNoMes >= 0.0) {
            return arrecadacaoNoMes;
        } else {
            throw new EstacionamentoException("Nenhum uso de vaga registrado ou valor inválido.");
        }
    }
    
    

    public double valorMedioPorUso() {
        double totalValorPago = 0.0;
        int totalUsos = 0;
    
        for (Cliente cliente : id) { // Usando 'id' em vez de 'clientes' para percorrer todos os clientes.
            for (Veiculo veiculo : cliente.getVeiculos()) {
                UsoDeVaga[] usos = veiculo.getUsosDeVaga();
                if (usos != null) {
                    for (UsoDeVaga uso : usos) {
                        totalValorPago += uso.valorPago();
                        totalUsos++;
                    }
                }
            }
        }
    
        if (totalUsos > 0) {
            return totalValorPago / totalUsos;
        } else {
            throw new EstacionamentoException("Nenhum uso de vaga registrado ou valor inválido.");
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
    
        if (topClientes[0] == null) { // Verifica se nenhum cliente foi encontrado
            throw new EstacionamentoException("Nenhum cliente encontrado para o mês especificado.");
        }
    
        return result.toString();
    }
    

    public List<Vaga> getClientes() {
        return null;
    }

    public List<Vaga> getVagas() {
        return null;
    }

}
