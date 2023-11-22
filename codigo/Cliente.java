import java.util.ArrayList;
import java.util.List;

public class Cliente {
    private String nome;
    private String id;
    private ArrayList<Veiculo> veiculos;
    private List<String> historico;
    private String categoria;
    private Turno turno;
    private static final double MENSALIDADE_TURNO = 200.0;
    private static final double MENSALIDADE_MENSALISTA = 500.0;

    public Cliente(String nome, String id, String categoria) {
        this.nome = nome;
        this.id = id;
        this.categoria = categoria;
        this.veiculos = new ArrayList<>();
        this.historico = new ArrayList<>();
    }

    public Cliente(String nome, String id, String categoria, Turno turno) {
        this(nome, id, categoria);
        this.turno = turno;
    }

    public void addVeiculo(Veiculo veiculo) {
        veiculos.add(veiculo);
    }

    public Veiculo possuiVeiculo(String placa) {
        for (Veiculo veiculo : veiculos) {
            if (veiculo.getPlaca().equals(placa)) {
                return veiculo;
            }
        }
        return null;
    }

    public int totalDeUsos() {
        int total = 0;
        for (Veiculo veiculo : veiculos) {
            total += veiculo.getNumUsos();
        }
        return total;
    }

    public double arrecadadoPorVeiculo(String placa) {
        Veiculo veiculo = possuiVeiculo(placa);
        if (veiculo != null) {
            return veiculo.getArrecadacao();
        }
        return 0.0;
    }

    public double arrecadadoTotal() {
        double total = 0.0;
        for (Veiculo veiculo : veiculos) {
            total += veiculo.getArrecadacao();
        }
        return total;
    }

    public double arrecadadoNoMes(int mes) {
        double total = 0.0;
        for (Veiculo veiculo : veiculos) {
            total += veiculo.getArrecadacaoNoMes(mes);
        }
        return total;
    }

    public void adicionarAoHistorico(String atividade) {
        historico.add(atividade);
    }

    public List<String> getHistorico() {
        return historico;
    }



    public double getMensalidade() {
        if (this.categoria.equals("turno")) {
            return MENSALIDADE_TURNO;
        } else if (this.categoria.equals("mensalista")) {
            return MENSALIDADE_MENSALISTA;
        } else {
            return 0.0;
        }
    }

    public Turno getTurno() {
        return this.turno;
    }

    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public ArrayList<Veiculo> getVeiculos() {
        return veiculos;
    }

    public Veiculo getVeiculo(String placa) {
        for (Veiculo veiculo : veiculos) {
            if (veiculo.getPlaca().equals(placa)) {
                return veiculo;
            }
        }
        return null;
    }


}
