import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * A classe Estacionamento representa um estacionamento com vagas e clientes.
 */
public class Estacionamento {

    // Scanner para entrada do teclado
    static Scanner teclado = new Scanner(System.in);

    // Atributos da classe
    private String nome;
    private HashMap<Cliente, Vaga> clientesEstacionados;
    private Vaga[] vagas;
    private int quantFileiras;
    private int vagasPorFileira;
    private LinkedList<Cliente> id; // Lista de clientes

    /**
     * Construtor da classe Estacionamento.
     * 
     * @param nome           Nome do estacionamento.
     * @param fileiras       Número de fileiras no estacionamento.
     * @param vagasPorFila   Número de vagas por fileira no estacionamento.
     */
    public Estacionamento(String nome, int fileiras, int vagasPorFila) {
        this.nome = nome;
        this.quantFileiras = fileiras;
        this.vagasPorFileira = vagasPorFila;
        id = new LinkedList<>();
        gerarVagas();
        clientesEstacionados = new HashMap<>();
    }

    /**
     * Método para adicionar um veículo a um cliente.
     * 
     * @param placa  Placa do veículo.
     * @param idCli  ID do cliente.
     * @throws ExcecaoVeiculoJaCadastrado Se o veículo já estiver cadastrado para o cliente.
     */
    public void addVeiculo(String placa, String idCli) throws ExcecaoVeiculoJaCadastrado {
        Cliente clienteEncontrado = null;
        for (Cliente c : id) {
            if (idCli.equals(c.getId())) {
                clienteEncontrado = c;
                break;
            }
        }
        if (clienteEncontrado != null && clienteEncontrado.possuiVeiculo(placa) != null) {
            throw new ExcecaoVeiculoJaCadastrado("Veículo já cadastrado para este cliente");
        } else {
            clienteEncontrado.addVeiculo(new Veiculo(placa));
        }
    }
    
    /**
     * Método para adicionar um cliente.
     * 
     * @param cliente Cliente a ser adicionado.
     * @throws ExcecaoClienteJaCadastrado Se o cliente já estiver cadastrado.
     */
    public void addCliente(Cliente cliente) throws ExcecaoClienteJaCadastrado {
        Cliente clienteEncontrado = null;
        for (Cliente c : id) {
            if (cliente.equals(c.getId())) {
                clienteEncontrado = c;
                break;
            }
        }
        if (clienteEncontrado != null) {
            throw new ExcecaoClienteJaCadastrado("Cliente já cadastrado no sistema!");
        } else {
            id.add(cliente);
        }
    }

    // Método privado para gerar as vagas do estacionamento
    private void gerarVagas() {
        int tam = quantFileiras * vagasPorFileira;
        vagas = new Vaga[tam];
        for (int fila = 0; fila < quantFileiras; fila++) {
            for (int numero = 1; numero <= vagasPorFileira; numero++) {
                vagas[fila * vagasPorFileira + (numero - 1)] = new Vaga((char) ('A' + fila), numero);
            }
        }
    }

    /**
     * Método para calcular o valor médio por uso do estacionamento.
     * 
     * @return Valor médio por uso do estacionamento.
     */
    public double valorMedioPorUso() {
        double resposta = 0.0;
        int totalUsos = 0;

        for (Cliente c : id) {
            if (c != null) {
                resposta += c.arrecadadoTotal();
                totalUsos += c.totalDeUsos();
            }
        }

        if (totalUsos > 0) {
            resposta /= totalUsos;
        }

        return resposta;
    }

    /**
     * Método para obter os top 5 clientes com maior arrecadação em um determinado mês.
     * 
     * @param mes Mês para o qual deseja-se obter os top clientes.
     * @return Nomes dos top 5 clientes.
     */
    public String top5Clientes(int mes) {
        Cliente[] topClientes = new Cliente[5];

        for (Cliente c : id) {
            if (c != null) {
                double valorDoCliente = c.arrecadadoNoMes(mes);

                for (int i = 0; i < 5; i++) {
                    if (topClientes[i] == null || valorDoCliente > topClientes[i].arrecadadoNoMes(mes)) {
                        for (int j = 4; j > i; j--) {
                            topClientes[j] = topClientes[j - 1];
                        }
                        topClientes[i] = c;
                        break;
                    }
                }
            }
        }

        String[] nomesTopClientes = new String[5];
        for (int i = 0; i < 5; i++) {
            if (topClientes[i] != null) {
                nomesTopClientes[i] = topClientes[i].getNome();
            }
        }

        return Arrays.toString(nomesTopClientes);
    }

    /**
     * Método para estacionar um veículo associado a um cliente em uma vaga disponível.
     * 
     * @param placa   Placa do veículo.
     * @param cliente Cliente que está estacionando o veículo.
     */
    public void estacionar(String placa, Cliente cliente) {
        Veiculo veiculo = cliente.possuiVeiculo(placa);
    
        if (veiculo != null) {
            for (Vaga vaga : vagas) {
                if (vaga.disponivel()) {
                    veiculo.estacionar(vaga);
                    clientesEstacionados.put(cliente, vaga);
                    break;
                }
            }
        }
    }
    
    public double sair(String placa) {
        for (Cliente cliente : id) {
            Veiculo veiculo = cliente.possuiVeiculo(placa);
            if (veiculo != null) {
                return veiculo.sair();
            }
        }
    
        return 0.0;
    }

    /**
     * Método para calcular o total arrecadado pelo estacionamento.
     * 
     * @return Total arrecadado pelo estacionamento*/
     
      public double arrecadacaoNoMes(int mes) {
		double total = 0.0;

		for (Cliente cliente : id) {
			total = total + cliente.arrecadadoNoMes(mes);
		}
		return total;
	}


	public String getNome() {
		return this.nome;
	}


	public void mostrarVagas(){
		for(int i =0; i < vagas.length; i++){
			System.out.println(i + "- status: "+ vagas[i].disponivel());
		}
	}
}
