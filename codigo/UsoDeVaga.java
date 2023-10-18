import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class UsoDeVaga implements Serializable {

    private static final double FRACAO_USO = 0.25;
    private static final double VALOR_FRACAO = 4.0;
    private static final double VALOR_MAXIMO = 50.0;
    private Vaga vaga;
    private LocalDateTime entrada;
    private LocalDateTime saida;
    private double valorPago;
    private List<ServicoAdicional> servicosAdicionais;

    public UsoDeVaga(Vaga vaga, List<ServicoAdicional> servicosAdicionais) {
        this.vaga = vaga;
        this.entrada = LocalDateTime.now();
        this.saida = null;
        this.valorPago = 0.0;
        this.servicosAdicionais = new ArrayList<>(servicosAdicionais);
    }

    public double sair() {
        this.saida = LocalDateTime.now();
        this.calcularValorPago();
        vaga.setOcupada(false);
        return this.valorPago;
    }

    private void calcularValorPago() {
        double valorBase = calcularValorBase();
        double valorServicos = calcularValorServicosAdicionais();
        this.valorPago = Math.min(valorBase + valorServicos, VALOR_MAXIMO);
    }

    private double calcularValorBase() {
        long minutos = entrada.until(saida, java.time.temporal.ChronoUnit.MINUTES);
        double valorBase = minutos * FRACAO_USO * VALOR_FRACAO;
        return valorBase;
    }

    private double calcularValorServicosAdicionais() {
        double valorServicos = 0.0;
        for (ServicoAdicional servico : servicosAdicionais) {
            valorServicos += servico.calcularValor();
        }
        return valorServicos;
    }

    public double valorPago() {
        return this.valorPago;
    }

    public void adicionarServico(ServicoAdicional servico) {
        servicosAdicionais.add(servico);
    }

    public void removerServico(ServicoAdicional servico) {
        servicosAdicionais.remove(servico);
    }
}

abstract class ServicoAdicional implements Serializable {
    protected double valor;
    protected int tempoMinimoPermanencia;

    public ServicoAdicional(double valor, int tempoMinimoPermanencia) {
        this.valor = valor;
        this.tempoMinimoPermanencia = tempoMinimoPermanencia;
    }

    public abstract double calcularValor();
}

class Manobrista extends ServicoAdicional {
    public Manobrista() {
        super(5.0, 0);
    }

    @Override
    public double calcularValor() {
        return this.valor;
    }
}

class Lavagem extends ServicoAdicional {
    public Lavagem() {
        super(20.0, 60); // 60 minutos = 1 hora
    }

    @Override
    public double calcularValor() {
        return this.valor;
    }
}

class Polimento extends ServicoAdicional {
    public Polimento() {
        super(45.0, 120); // 120 minutos = 2 horas
    }

    @Override
    public double calcularValor() {
        return this.valor;
    }
}
