import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

class UsoDeVagaHorista extends UsoDeVaga {

    public UsoDeVagaHorista(Vaga vaga, Cliente cliente) throws UsoDeVagaException {
        super(vaga, cliente);
    }

    public UsoDeVagaHorista(Vaga vaga, Cliente cliente, Servico servico) throws UsoDeVagaException {
        super(vaga, cliente, servico);
    }

    @Override
    public double sair() throws UsoDeVagaException {
        // Implementação específica para cliente Horista
        this.saida = LocalDateTime.now();
        this.valorPago = calcularValorHorista();
        vaga.setOcupada(false);
        return this.valorPago;
    }

    @Override
    public double valorPago() {
        // Implementação específica para cliente Horista
        return calcularValorHorista();
    }

    private double calcularValorHorista() {
        long tempo = ChronoUnit.HOURS.between(entrada, saida);

        if (servico != null) {
            this.valorPago += servico.getPrecoPorHora();
        }

        if (tempo < FRACAO_USO) {
            this.valorPago += VALOR_FRACAO;
        } else {
            this.valorPago += Math.min((tempo / FRACAO_USO) * VALOR_FRACAO, VALOR_MAXIMO);
        }

        return this.valorPago;
    }
}
