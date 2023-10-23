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

    public Estacionamento(String nome, Cliente[] id, List<Vaga> vagas, int quantFileiras, int vagasPorFileira) {
        this.nome = nome;
        this.id = id;
        this.vagas = vagas;
        this.quantFileiras = quantFileiras;
        this.vagasPorFileira = vagasPorFileira;
    }

    public void addVeiculo(Veiculo veiculo, String idCli) throws ClienteNaoEncontradoException {
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
            throw new Exception("Cliente nao encontrado.");
        }
    }
   
   
    public void addCliente(String nome, String id){
        if(clienteJaExiste(id)){

        } else{
            cliente.add(new Cliente(nome,id));
        }
    }
   
    private boolean clienteJaExiste(String id) {
        for (Cliente c : cliente){
            if(c.getId().equals(id)){
                return true;
            }
        }
        return false;
    }
    
    public void addVagas(String id, boolen disponivel) {
        if(vagaJaExiste(id)){

        } else{
            vagas.add(new Vaga(id, disponivel));
        }
    }

    private boolean vagaJaExiste(String id){
        for(Vaga v : vagas){
            if(v.getId().equals(id)){
                return true;
            }
        }
        return false;
    }

    private void gerarVagas() {
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
        SUCCESS,
        VAGA_OCUPADA,
        VEICULO_NAO_ENCONTRADO,
        SEM_VAGAS_DISPONIVEIS
    }
    
    public EstacionamentoStatus estacionar(String placa) {
        for (Vaga vaga : vagas) {
            if (vaga.disponivel()) {
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
                        return EstacionamentoStatus.SUCCESS;
                    } else {
                        return EstacionamentoStatus.VAGA_OCUPADA;
                    }
                } else {
                    return EstacionamentoStatus.VEICULO_NAO_ENCONTRADO;
                }
            }
        }
        return EstacionamentoStatus.SEM_VAGAS_DISPONIVEIS;
    }  

    public double sair(String placa) {
        for (Cliente cliente : id) {
            Veiculo veiculo = cliente.possuiVeiculo(placa);
            if (veiculo != null) {
                UsoDeVaga[] usos = veiculo.getUsosDeVaga();
                for (UsoDeVaga uso : usos) {
                    if (uso.sair()) {
                        double valorPago = uso.valorPago();
                        return valorPago;
                    }
                }
            }
        }
        return -1.0; 
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
                if (cliente.getVeiculos() != null) {
                    for (Veiculo veiculo : cliente.getVeiculos()) {
                        if (veiculo.getUsosDeVaga() != null) {
                            for (UsoDeVaga uso : veiculo.getUsosDeVaga()) {
                                totalValorPago += uso.valorPago();
                                totalUsos++;
                            }
                        }
                    }
                }
            }
    
            if (totalUsos > 0) {
                return totalValorPago / totalUsos;
            } else {
                // Retorna -1.0 quando não há usos registrados.
                return -1.0;
            }
        } else {
            // Retorna -1.0 quando não há clientes cadastrados.
            return -1.0;
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
