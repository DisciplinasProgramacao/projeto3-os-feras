import java.util.ArrayList;

public class Cliente {
    private String nome;
    private String id;
    private ArrayList<Veiculo> veiculos;

    public Cliente(String nome, String id) {
        this.nome = nome;
        this.id = id;
        this.veiculos = new ArrayList<>();
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
}
