import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Veiculo {
    private String placa;
    private List<UsoDeVaga> historicoVagas; // Lista para armazenar o histórico
    private Date dataEntrada;

    public Veiculo(String placa) {
        this.placa = placa;
        this.historicoVagas = new ArrayList<>();
        this.dataEntrada = new Date();
    }

    public void estacionar(Vaga vaga) throws VeiculoException {
        for (UsoDeVaga uso : historicoVagas) {
            if (uso.getVaga() == null) {
                uso.setVaga(vaga);
                uso.setDataEntrada(new Date());
                return; // Estacionamento bem-sucedido, retornar sem exceção
            }
        }
        throw new VeiculoException("Não foi possível estacionar. Não há vagas disponíveis.");
    }

    public double sair() {
        double total = 0;
        Date dataSaida = new Date();

        for (UsoDeVaga uso : historicoVagas) {
            Vaga vaga = uso.getVaga();
            if (vaga != null) {
                vaga.setValorPago(0); // Zerar o valor da vaga após a saída
                uso.setDataSaida(dataSaida); // Registrar a data de saída
                total += vaga.getValorPago();
            }
        }

        historicoVagas.clear(); // Limpar o histórico de vagas

        return total;
    }

    public double totalArrecadado() {
        double total = 0;
        for (UsoDeVaga uso : historicoVagas) {
            Vaga vaga = uso.getVaga();
            if (vaga != null) {
                total += vaga.getValorPago();
            }
        }
        return total;
    }

    public double arrecadadoNoMes(int mes) {
        double total = 0;
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
}
