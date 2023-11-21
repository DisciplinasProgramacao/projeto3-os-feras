import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.io.Serializable;

public class Veiculo implements Serializable {
    private String placa;
    private List<UsoDeVaga> historicoVagas;
    private Date dataEntrada;

    public Veiculo(String placa) {
        this.placa = placa;
        this.historicoVagas = new ArrayList<>();
        this.dataEntrada = new Date();
    }

  public void estacionar(Vaga vaga) {
    UsoDeVaga uso = new UsoDeVaga(vaga);
    historicoVagas.add(uso);
}


    public double sair(String placa) {
    double totalArrecadado = 0.0;
    Date dataSaida = new Date();

    for (UsoDeVaga uso : historicoVagas) {
        Vaga vaga = uso.getVaga();
        if (vaga != null && vaga.getVeiculo().getPlaca().equals(placa)) {
            vaga.setValorPago(0);
            uso.setDataSaida(dataSaida);
            totalArrecadado += vaga.getValorPago();
        }
    }

    historicoVagas.removeIf(uso -> uso.getVaga().getVeiculo().getPlaca().equals(placa));

    return totalArrecadado;
}


   public double totalArrecadado() {
    double total = 0.0;
    for (UsoDeVaga uso : historicoVagas) {
        Vaga vaga = uso.getVaga();
        if (vaga != null) {
            total += vaga.getValorPago();
        }
    }
    return total;
}


   public double arrecadadoNoMes(int mes) {
    double total = 0.0;
    for (UsoDeVaga uso : historicoVagas) {
        Vaga vaga = uso.getVaga();
        if (vaga != null && vaga.getMes() == mes) {
            total += vaga.getValorPago();
        }
    }
    return total;
}


    public int totalDeUsos() {
    return historicoVagas.size();
}


    public String getPlaca() {
        return placa;
    }
    
    public List<UsoDeVaga> gerarRelatorio(boolean ordenarPorData) {
        List<UsoDeVaga> relatorio = new ArrayList<>(historicoVagas);

        if (ordenarPorData) {
            relatorio.sort(Comparator.comparing(UsoDeVaga::getDataEntrada));
        } else {
            relatorio.sort(Comparator.comparing(UsoDeVaga::getValorPago).reversed());
        }

        return relatorio;
    }
}
