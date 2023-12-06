import java.time.LocalDateTime;

class UsoDeVagaMensalista extends UsoDeVaga {

    public UsoDeVagaMensalista(Vaga vaga, Cliente cliente) throws UsoDeVagaException {
        super(vaga, cliente);
    }

    public UsoDeVagaMensalista(Vaga vaga, Cliente cliente, Servico servico) throws UsoDeVagaException {
        super(vaga, cliente, servico);
    }

    @Override
    public double sair() throws UsoDeVagaException {
        // Implementação específica para cliente Mensalista
        this.saida = LocalDateTime.now();
        this.valorPago = calcularValorMensalista();
        vaga.setOcupada(false);
        return this.valorPago;
    }

    @Override
    public double valorPago() {
        // Implementação específica para cliente Mensalista
        return calcularValorMensalista();
    }

    private double calcularValorMensalista() {
        // Lógica de cálculo para mensalista
        return this.valorPago;
    }
}
