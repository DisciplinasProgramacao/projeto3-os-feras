import java.time.LocalDateTime;

class UsoDeVagaTurno extends UsoDeVaga {

    public UsoDeVagaTurno(Vaga vaga, Cliente cliente) throws UsoDeVagaException {
        super(vaga, cliente);
    }

    public UsoDeVagaTurno(Vaga vaga, Cliente cliente, Servico servico) throws UsoDeVagaException {
        super(vaga, cliente, servico);
    }

    @Override
    public double sair() throws UsoDeVagaException {
        // Implementação específica para cliente Turno
        this.saida = LocalDateTime.now();
        this.valorPago = calcularValorTurno();
        vaga.setOcupada(false);
        return this.valorPago;
    }

    @Override
    public double valorPago() {
        // Implementação específica para cliente Turno
        return calcularValorTurno();
    }

    private double calcularValorTurno() {
        // Lógica de cálculo para cliente turno
        return this.valorPago;
    }
}
