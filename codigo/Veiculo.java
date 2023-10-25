public class Veiculo {
    private String placa;
    private UsoDeVaga[] usos;

    public Veiculo(String placa) {
        this.placa = placa;
        this.usos = new UsoDeVaga[500];
    }

    public void estacionar(Vaga vaga) throws VeiculoException {
        for (int i = 0; i < usos.length; i++) {
            if (usos[i] == null) {
                usos[i] = new UsoDeVaga(vaga);
                return; // Estacionamento bem-sucedido, retornar sem exceção
            }
        }
        throw new VeiculoException("Não foi possível estacionar. Não há vagas disponíveis.");
    }

    public double sair() throws VeiculoException {
        double total = 0;
        for (int i = 0; i < usos.length; i++) {
            if (usos[i] != null) {
                total += usos[i].getVaga().getValorPago();
                usos[i] = null;
            }
        }
        
        if (total == 0) {
            throw new VeiculoException("O veículo não foi registrado no estacionamento." )
        }
    }

    public double totalArrecadado() throws VeiculoException {
        double total = 0;
        for (int i = 0; i < usos.length; i++) {
            if (usos[i] != null) {
                total += usos[i].getVaga().getValorPago();
            }
        }
        if (total == 0) {
            throw new VeiculoException("Não foi arrecadado nenhum valor.")
        }
    }

    public double arrecadadoNoMes(int mes) throws VeiculoException {
        double total = 0;
        for (int i = 0; i < usos.length; i++) {
            if (usos[i] != null && usos[i].getVaga().getMes() == mes) {
                total += usos[i].getVaga().getValorPago();
            }
        }
        
        if (total == 0) {
            throw new VeiculoException("Não foi arrecadado nenhum valor.")
        }
    }

    public int totalDeUsos() throws VeiculoException {
        int total = 0;
        for (int i = 0; i < usos.length; i++) {
            if (usos[i] != null) {
                total++;
            }
        }
        
        if (total == 0) {
            throw new VeiculoException("Não houve casos de uso do veículo.")
        }
    }

}
