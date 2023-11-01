import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class Veiculo {
    private String placa;
    private List<HistoricoVaga> historicoVagas; // Lista para armazenar o histórico

    public Veiculo(String placa) {
        this.placa = placa;
        this.historicoVagas = new ArrayList<>();
    }

    public void estacionar(Vaga vaga) throws VeiculoException {
        for (HistoricoVaga historico : historicoVagas) {
            if (historico.getUsoDeVaga() == null) {
                historico.setUsoDeVaga(new UsoDeVaga(vaga));
                return; // Estacionamento bem-sucedido, retornar sem exceção
            }
        }
        throw new VeiculoException("Não foi possível estacionar. Não há vagas disponíveis.");
    }

    public double sair() {
        double total = 0;
        for (HistoricoVaga historico : historicoVagas) {
            UsoDeVaga uso = historico.getUsoDeVaga();
            if (uso != null) {
                total += uso.getVaga().getValorPago();
                uso.getVaga().setValorPago(0); // Zerar o valor da vaga após a saída
                uso.setDataSaida(new Date()); // Registrar a data de saída
                historico.setUsoDeVaga(null); // Remover o uso da vaga do histórico
            }
        }
        return total;
    }

    public double totalArrecadado() {
        double total = 0;
        for (HistoricoVaga historico : historicoVagas) {
            UsoDeVaga uso = historico.getUsoDeVaga();
            if (uso != null) {
                total += uso.getVaga().getValorPago();
            }
        }
        return total;
    }

    public double arrecadadoNoMes(int mes) {
        double total = 0;
        for (HistoricoVaga historico : historicoVagas) {
            UsoDeVaga uso = historico.getUsoDeVaga();
            if (uso != null && uso.getVaga().getMes() == mes) {
                total += uso.getVaga().getValorPago();
            }
        }
        return total;
    }

    public int totalDeUsos() {
        int total = 0;
        for (HistoricoVaga historico : historicoVagas) {
            if (historico.getUsoDeVaga() != null) {
                total++;
            }
        }
        return total;
    }
}

class HistoricoVaga {
    private UsoDeVaga usoDeVaga;
    private Date dataEntrada;

    public HistoricoVaga() {
        this.usoDeVaga = null;
        this.dataEntrada = new Date();
    }

    public UsoDeVaga getUsoDeVaga() {
        return usoDeVaga;
    }

    public void setUsoDeVaga(UsoDeVaga usoDeVaga) {
        this.usoDeVaga = usoDeVaga;
    }

    public Date getDataEntrada() {
        return dataEntrada;
    }
}
